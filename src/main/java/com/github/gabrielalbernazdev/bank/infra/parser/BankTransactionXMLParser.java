package com.github.gabrielalbernazdev.bank.infra.parser;

import com.github.gabrielalbernazdev.bank.domain.model.BankTransaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankTransactionXMLParser implements BankTransactionParser {
    @Override
    public List<BankTransaction> parse(List<String> lines) {
        List<BankTransaction> transactions = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line);
        }

        String linesString = sb.toString();

        try {
            ByteArrayInputStream input = new ByteArrayInputStream(linesString.getBytes(StandardCharsets.UTF_8));
            Document dom = builder.parse(input);
            Element root = dom.getDocumentElement();
            NodeList transactionElements = root.getElementsByTagName("transaction");

            for (int i = 0; i < transactionElements.getLength(); i++) {
                Node transactionNode = transactionElements.item(i);
                if(transactionNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element transactionElement = (Element) transactionNode;
                    UUID id = UUID.fromString(transactionElement.getElementsByTagName("id").item(0).getTextContent());
                    double amount  = Double.parseDouble(transactionElement.getElementsByTagName("amount").item(0).getTextContent());
                    String description = transactionElement.getElementsByTagName("description").item(0).getTextContent();
                    LocalDateTime createdAt = LocalDateTime.parse(transactionElement.getElementsByTagName("createdAt").item(0).getTextContent());
                    transactions.add(new BankTransaction(id, amount, description, createdAt));
                }
            }
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}
