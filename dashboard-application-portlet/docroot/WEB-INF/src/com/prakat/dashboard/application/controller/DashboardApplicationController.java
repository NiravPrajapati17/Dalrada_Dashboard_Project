package com.prakat.dashboard.application.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class NewPortlet
 */
public class DashboardApplicationController extends MVCPortlet {
	
	private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    
	 public void orderFileController(ActionRequest actionRequest,
				ActionResponse actionResponse) throws IOException, PortletException {

		 
	        String csvFile = "C://LR7/data/order_new.csv";

	        Scanner scanner = new Scanner(new File(csvFile));
	        while (scanner.hasNext()) {
	            List<String> line = parseLine(scanner.nextLine());
	           /* System.out.println("Country [id= " + line.get(0) + ", code= " + line.get(1) + " , name=" + line.get(2) + "]");
	            System.out.println(line.get(7)+" sfgsf "+line.get(8));*/
	            
	            Double Tracking_Numbers = Double.parseDouble(line.get(17));
	            String normalDoubleValue = String.format("%.0f",Tracking_Numbers);
	            
	            System.out.println("Order_Date :: "+line.get(0) + ", PO_Number :: "+ line.get(2) +", Reference_Number :: "+ line.get(3)
	            		+" ,Channel_Name :: "+line.get(4)+", Order_Status :: "+line.get(5) + ", SKU :: "+line.get(6)+",Product_Name :: "+line.get(7)
	            		+", Quantity :: "+line.get(8)+", Supplier_Cost_Per_SKU :: "+line.get(9)+", Sale_Price :: "+line.get(10)+", Supplier_Cost_Total :: "+line.get(11)+
	            		", Order_Total :: "+line.get(12)+", Supplier_Name :: "+line.get(13)+", Store_Shipping_Method :: "+line.get(14)+", Shipping_Carrier :: "+line.get(15)+
	            		", Shipping_Method :: "+line.get(16)+", Tracking_Numbers :: "+normalDoubleValue+", Customer_Name :: "+line.get(18)+", Customer_Email :: "+line.get(19)+
	            		", Customer_Phone :: "+line.get(20)+", Address_Line_1 :: "+line.get(21)+", Address_Line_2 :: "+line.get(22)+", City :: "+line.get(23)+", State :: "+line.get(24)+
	            		",Zip :: "+line.get(25)+", Country :: "+line.get(26));
	        }
	        scanner.close();

	    }

	    public static List<String> parseLine(String cvsLine) {
	        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	    }

	    public static List<String> parseLine(String cvsLine, char separators) {
	        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	    }

	    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

	        List<String> result = new ArrayList<String>();

	        //if empty, return!
	        if (cvsLine == null && cvsLine.isEmpty()) {
	            return result;
	        }

	        if (customQuote == ' ') {
	            customQuote = DEFAULT_QUOTE;
	        }

	        if (separators == ' ') {
	            separators = DEFAULT_SEPARATOR;
	        }

	        StringBuffer curVal = new StringBuffer();
	        boolean inQuotes = false;
	        boolean startCollectChar = false;
	        boolean doubleQuotesInColumn = false;

	        char[] chars = cvsLine.toCharArray();

	        for (char ch : chars) {

	            if (inQuotes) {
	                startCollectChar = true;
	                if (ch == customQuote) {
	                    inQuotes = false;
	                    doubleQuotesInColumn = false;
	                } else {

	                    //Fixed : allow "" in custom quote enclosed
	                    if (ch == '\"') {
	                        if (!doubleQuotesInColumn) {
	                            curVal.append(ch);
	                            doubleQuotesInColumn = true;
	                        }
	                    } else {
	                        curVal.append(ch);
	                    }

	                }
	            } else {
	                if (ch == customQuote) {

	                    inQuotes = true;

	                    //Fixed : allow "" in empty quote enclosed
	                    if (chars[0] != '"' && customQuote == '\"') {
	                        curVal.append('"');
	                    }

	                    //double quotes in column will hit this!
	                    if (startCollectChar) {
	                        curVal.append('"');
	                    }

	                } else if (ch == separators) {

	                    result.add(curVal.toString());

	                    curVal = new StringBuffer();
	                    startCollectChar = false;

	                } else if (ch == '\r') {
	                    //ignore LF characters
	                    continue;
	                } else if (ch == '\n') {
	                    //the end, break!
	                    break;
	                } else {
	                    curVal.append(ch);
	                }
	            }

	        }

	        result.add(curVal.toString());

	        return result;
	    }

	   
	}