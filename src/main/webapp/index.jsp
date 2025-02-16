<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.github.gabrielalbernazdev.util.constants.PathConstants" %>
<%@ page import="com.github.gabrielalbernazdev.util.constants.ParamConstants" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="REQUEST_URL" value="${PathConstants.BANK_TRANSACTION_INDEX_PATH}" />
<c:set var="FILE_PARAM" value="${ParamConstants.BANK_TRANSACTION_FILE_PARAM}" />
<c:set var="NAME_PARAM" value="${ParamConstants.BANK_TRANSACTION_NAME_FILTER_PARAM}" />
<c:set var="AMOUNT_PARAM" value="${ParamConstants.BANK_TRANSACTION_AMOUNT_FILTER_PARAM}" />
<c:set var="DATE_PARAM" value="${ParamConstants.BANK_TRANSACTION_DATE_FILTER_PARAM}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>BankAnalyzer</title>
  <style>
    html, body, div, span, applet, object, iframe,
    h1, h2, h3, h4, h5, h6, p, blockquote, pre,
    a, abbr, acronym, address, big, cite, code,
    del, dfn, em, img, ins, kbd, q, s, samp,
    small, strike, strong, sub, sup, tt, var,
    b, u, i, center,
    dl, dt, dd, ol, ul, li,
    fieldset, form, label, legend,
    table, caption, tbody, tfoot, thead, tr, th, td,
    article, aside, canvas, details, embed,
    figure, figcaption, footer, header, hgroup,
    menu, nav, output, ruby, section, summary,
    time, mark, audio, video {
      margin: 0;
      padding: 0;
      border: 0;
      font-size: 100%;
      font: inherit;
      vertical-align: baseline;
    }
    article, aside, details, figcaption, figure,
    footer, header, hgroup, menu, nav, section {
      display: block;
    }
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      line-height: 1;
    }
    ol, ul {
      list-style: none;
    }
    blockquote, q {
      quotes: none;
    }
    blockquote:before, blockquote:after,
    q:before, q:after {
      content: '';
      content: none;
    }
    table {
      border-collapse: collapse;
      border-spacing: 0;
    }
    :root {
      --primary: #f33;
      --header-height: 60px;
    }
    .container {
      max-width: 1280px;
      margin: 0 auto;
    }
    #main-container {
      display: flex;
      flex-direction: column;
      align-items: stretch;
      min-height: calc(100vh - var(--header-height));
      background-color: #fbfbfb;
      box-sizing: border-box;
    }
    #main-container .header {
      padding: 20px;
      max-height: var(--header-height);
      background: #fff;
      box-shadow: 0 1px 0 0 rgba(17, 17, 17, 0.07);
    }
    #main-container .header .container {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    #main-container .header .container .brand {
      display: flex;
      align-items: center;
      text-decoration: none;
    }
    #main-container .header .container .brand .icon {
      width: 32px;
      height: 32px;
      background: linear-gradient(45deg, #ff6b6b, #f06595);
      border-radius: 4px;
      margin-right: 10px;
    }
    #main-container .header .container .brand .text {
      font-size: 24px;
      font-weight: bold;
      color: #333;
    }
    #main-container .main .titles {
      padding: 60px 10px;
      text-align: center;
    }
    #main-container .main h1.main-title {
      font-size: 33px;
      font-weight: 600;
      line-height: 44px;
      letter-spacing: -1.3px;
      color: #f33;
    }
    #main-container .main h2.main-subtitle {
      font-size: 18px;
      line-height: 28px;
    }
    #main-container .main .actions-container {
      background: #fff;
      padding: 30px;
      margin: 20px auto;
      max-width: 800px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      border-radius: 4px;
    }
    #main-container .main .actions-container h3 {
      margin-bottom: 15px;
      font-size: 20px;
      color: #333;
    }
    #main-container .main .actions-container form {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
      gap: 15px;
    }
    #main-container .main .actions-container label {
      display: block;
      font-size: 14px;
      margin-bottom: 5px;
      color: #555;
    }
    #main-container .main .actions-container input[type="file"],
    #main-container .main .actions-container input[type="text"],
    #main-container .main .actions-container input[type="number"],
    #main-container .main .actions-container input[type="date"] {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    #main-container .main .actions-container .full-width {
      grid-column: 1 / -1;
    }
    #main-container .main .actions-container button {
      padding: 10px 20px;
      background: var(--primary);
      border: none;
      color: #fff;
      border-radius: 4px;
      cursor: pointer;
    }
    #main-container .main .table-container {
      background: #fff;
      padding: 30px;
      margin: 20px auto;
      max-width: 800px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      border-radius: 4px;
      overflow-x: auto;
    }
    #main-container .main .table-container table {
      width: 100%;
      border-collapse: collapse;
    }
    #main-container .main .table-container th,
    #main-container .main .table-container td {
      padding: 12px 15px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }
    #main-container .main .table-container th {
      background: #f2f2f2;
      font-weight: 600;
      color: #333;
    }
    #main-container .main .table-container tr:hover {
      background: #f9f9f9;
    }
    .alert-container {
      display:flex;
      align-items: center;
      justify-content:center;
      padding 20px 0;
    }
    .alert-container .alert {
      position: relative;
      padding: 15px 20px 15px 15px;
      border-radius: 4px;
      margin: 10px 0;
      font-family: Arial, sans-serif;
      min-width: 400px;
      max-width: 800px;
    }
    .alert-container .alert.alert-error {
      border: 1px solid var(--primary);
      background-color:rgb(255, 146, 146);
      color: #var(--primary);
    }
    .alert-container .alert.alert-error .close-btn {
      position: absolute;
      top: 5px;
      right: 10px;
      background: none;
      border: none;
      font-size: 22px;
      cursor: pointer;
      color: #8a6d3b;
    }
    .alert-container .alert.alert-error .close-btn:hover {
      color: #000;
    }
    @media screen and(max-width: 767px) {
      #main-container .header .container {
        justify-content: center;
      }
    }
  </style>
</head>
<body>
  <div id="main-container">
    <header class="header">
      <div class="container">
        <div class="brand">
          <div class="icon"></div>
          <div class="text">BankAnalyzer</div>
        </div>
      </div>
    </header>
    <main class="main">
      <div class="titles">
        <h1 class="main-title">Analyze transactions</h1>
        <h2 class="main-subtitle">The best way to analyze bank transactions in different formats</h2>
      </div>
      <c:if test="${error != null && error.length() > 0}" >
        <div class="alert-container container">
          <div class="alert alert-error">
            ${error}
            <button class="close-btn">&times;</button>
          </div>
        </div>
      </c:if>
      <div class="actions-container">
        <h3>Upload &amp; Filter Transactions</h3>
        <form id="action-form" action="<c:out value = "${REQUEST_URL}"/>" method="post" enctype="multipart/form-data">
          <div class="full-width">
            <label for="file-upload">Choose the transaction file (CSV, JSON, XML)</label>
            <input type="file" id="file-upload" name="${FILE_PARAM}" accept="application/json, .csv, .xml">
          </div>
          <div hidden>
            <label for="filter-name">Name</label>
            <input type="text" id="filter-name" name="${NAME_PARAM}" placeholder="Transaction Name">
          </div>
          <div hidden>
            <label for="filter-amount">Amount</label>
            <input type="number" id="filter-amount" name="${AMOUNT_PARAM}" placeholder="Transaction Amount">
          </div>
          <div hidden>
            <label for="filter-date">Date</label>
            <input type="date" id="filter-date" name="${DATE_PARAM}">
          </div>
          <div class="full-width" style="text-align: right;" hidden>
            <button type="submit" disabled>Process Transactions</button>
          </div>
        </form>
      </div>
      <c:if test="${transactions.size() > 0}">
          <div class="table-container" id="table-container">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Amount</th>
                  <th>Description</th>
                  <th>Date</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${transactions}" var="transaction">
                    <tr>
                        <td>${transaction.id}</td>
                        <td style="color: ${transaction.amount < 0 ? 'red' : 'green'};"><fmt:formatNumber value="${transaction.amount}" type="currency" /></td>
                        <td>${transaction.description}</td>
                        <td>${transaction.createdAt}</td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
      </c:if>
    </main>
  </div>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
      const alertCloseBtns = document.querySelectorAll('.alert-container .alert .close-btn');
      const fileInput = document.getElementById('file-upload');
      const form = document.getElementById('action-form');
      const additionalElements = Array.from(form.children).slice(1);
      const submitButton = form.querySelector('button[type="submit"]');
      function updateFormState() {
        const fileIsEmpty = fileInput.files && fileInput.files.length === 0;
        additionalElements.forEach(el => { el.hidden = fileIsEmpty; });
        submitButton.disabled = fileIsEmpty;
      }
      function closeAlert(e) {
        e.target.closest('.alert-container')?.remove();
      }
      updateFormState();
      alertCloseBtns.forEach(btn => btn.addEventListener('click', closeAlert));
      fileInput.addEventListener('change', updateFormState);
    }); 
  </script>
</body>
</html>
