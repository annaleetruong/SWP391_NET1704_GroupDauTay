/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author hj
 */
public class OrderDTO {
    private String orderID;
    private String accountID;
    private String staffID;
    private String form_Receipt;
    private String ShipperID;
    private Date orderDate;
    private Date receiptDate;
    private float discount;
    private float delivery_charges;
    private float total_Order;
    private float total_order_final;
    private String payBy;
    private String status;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String accountID, String staffID, String form_Receipt, String ShipperID, Date orderDate, Date receiptDate, float discount, float delivery_charges, float total_Order, String payBy, String status) {
        this.orderID = orderID;
        this.accountID = accountID;
        this.staffID = staffID;
        this.form_Receipt = form_Receipt;
        this.ShipperID = ShipperID;
        this.orderDate = orderDate;
        this.receiptDate = receiptDate;
        this.discount = discount;
        this.delivery_charges = delivery_charges;
        this.total_Order = total_Order;
        this.payBy = payBy;
        this.status = status;
    }

    public OrderDTO(String orderID, String form_Receipt, Date orderDate, float discount, float delivery_charges, float total_Order, String payBy, String status) {
        this.orderID = orderID;
        this.form_Receipt = form_Receipt;
        this.orderDate = orderDate;
        this.discount = discount;
        this.delivery_charges = delivery_charges;
        this.total_Order = total_Order;
        this.payBy = payBy;
        this.status = status;
    }

    public float getTotal_order_final() {
        return this.total_Order - this.total_Order * this.discount + this.delivery_charges;
    }

    public void setTotal_order_final(float total_order_final) {
        this.total_order_final = total_order_final;
    }
    
    
    
    

    public float getDelivery_charges() {
        return delivery_charges;
    }

    public void setDelivery_charges(float delivery_charges) {
        this.delivery_charges = delivery_charges;
    }

   

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getForm_Receipt() {
        return form_Receipt;
    }

    public void setForm_Receipt(String form_Receipt) {
        this.form_Receipt = form_Receipt;
    }

    public String getShipperID() {
        return ShipperID;
    }

    public void setShipperID(String ShipperID) {
        this.ShipperID = ShipperID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotal_Order() {
        return total_Order;
    }

    public void setTotal_Order(float total_Order) {
        this.total_Order = total_Order;
    }

    public String getPayBy() {
        return payBy;
    }

    public void setPayBy(String payBy) {
        this.payBy = payBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    
    
    
}
