package com.test.webscraping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.sbicard.com/en/personal/offer/travel-deal-july23.page";

        List<List<String>> tableData = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            List<List<String>> tabelData = extractTableData(doc);
            printTableData(tabelData);
            // Element element = doc.body();

            // Element table = doc.selectFirst("table");

            // Elements tableBody = table.select("tbody");
            // Element header = tableBody.first();
            // String[] headerNames =
            // header.stream().map(Element::text).toArray(String[]::new);

            // Set<String> sett = new HashSet<>();
            // for(int i=0; i<headerNames.length; i++) {
            // if(i==1) {
            // System.out.println("header name "+(i+1)+" ----"+headerNames[i]);
            // }
            // sett.add(headerNames[i]);
            // }

            // String[] columnNames =
            // tableBody.stream().map(Element::text).toArray(String[]::new);

            // for(String headerName : columnNames) {
            // System.out.println("header name -"+headerName);
            // }
            // System.out.println("column names - "+columnNames.toString());
            // System.out.println(table);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void printTableData(List<List<String>> tableData) {
        // for(List<String> listData : tableData) {
        //     for(String data : listData) {
        //         System.out.print(data+"     ");
        //     }
        //     System.out.println();
        // }

        int[] columnWidths = new int[tableData.get(0).size()];
        for (List<String> row : tableData) {
            for (int i = 0; i < row.size(); i++) {
                columnWidths[i] = Math.max(columnWidths[i], row.get(i).length());
            }
        }

        // Print the table data
        for (List<String> row : tableData) {
            for (int i = 0; i < row.size(); i++) {
                System.out.printf("%-" + columnWidths[i] + "s ", row.get(i));
            }
            System.out.println();
        }
    }

    private static List<List<String>> extractTableData(Document doc) {
        List<List<String>> tableData = new ArrayList<>();

        Element tbody = doc.select("table tbody").first();
        if (tbody == null) {
            System.err.println("No tbody found in the document");
            return tableData;
        }

        // Elements rowList = tbody.select("tr");
        // System.out.println(rowList);
        for (Element row : tbody.select("tr")) {
            List<String> rowData = new ArrayList<>();
            for (Element cell : row.select("td")) {
                rowData.add(cell.text());
            }
            tableData.add(rowData);
        }

        return tableData;
    }
}
