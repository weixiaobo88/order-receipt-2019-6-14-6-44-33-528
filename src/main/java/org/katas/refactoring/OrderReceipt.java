package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        String header = "======Printing Orders======\n";
        String customerDetail = order.getCustomerName() + order.getCustomerAddress();
        String totalSalesTaxDetail = "Sales Tax\t" + getTotalSalesTax(order);
        String totalAmount = "Total Amount\t" + calculateTotal(order);

        return output
                .append(header)
                .append(customerDetail)
                .append(getLineItemsDetail(order))
                .append(totalSalesTaxDetail)
                .append(totalAmount)
                .toString();
    }

    private double calculateTotal(Order order) {
        double total = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            total += (lineItem.totalAmount() + getSalesTax(lineItem));
        }
        return total;
    }

    private double getTotalSalesTax(Order order) {
        double totalSalesTax = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            totalSalesTax += getSalesTax(lineItem);
        }
        return totalSalesTax;
    }

    private String getLineItemsDetail(Order order) {
        String lineItemsDetail = "";
        for (LineItem lineItem : order.getLineItems()) {
            lineItemsDetail += (lineItem.getDescription() + '\t' + lineItem.getPrice() + '\t' + lineItem.getQuantity() + '\t' + lineItem.totalAmount() + '\n');
        }
        return lineItemsDetail;
    }

    private double getSalesTax(LineItem lineItem) {
        return lineItem.totalAmount() * .10;
    }
}