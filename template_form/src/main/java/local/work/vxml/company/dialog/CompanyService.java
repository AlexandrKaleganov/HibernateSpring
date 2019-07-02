package local.work.vxml.company.dialog;

import local.work.vxml.base.AppGeneralException;
import local.work.vxml.base.interfaces.IGlobalConfigService;
import local.work.vxml.base.interfaces.ISessionService;
import local.work.vxml.company.dialog.vars.VARS;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.Date;

@SuppressWarnings("ALL")
public class CompanyService {

    @Autowired
    private IGlobalConfigService globalConfigService;

    @Autowired
    private ISessionService sessionService;

    private BasicDataSource companyDataSource;

    public CompanyService(BasicDataSource companyDataSource) {
        this.companyDataSource = companyDataSource;
    }

    public Order insertPhone(String phone) throws AppGeneralException {
        phone = normAni(phone);
        Order order = new Order(globalConfigService, companyDataSource, sessionService);
        try (Connection conn = companyDataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `call_history`\n" +
                    "(`ani`, session_id,`created`) VALUES (?, ?, now())", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, phone);
            pstmt.setString(2, sessionService.getSessionID());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.id = generatedKeys.getLong(1);
                    order.phone = phone;
                    return order;
                } else {
                    Logger.getLogger(getClass()).error("Insert in Schema 'client' - Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass()).error(e.getMessage(), e);
        }
        Logger.getLogger(getClass()).warn("Order is NULL");
        return null;
    }

    /**
     * Добавляем ответ Клиента в БД.
     * @param dialog
     * @param answer
     * @throws AppGeneralException
     */
    public void addAnswer(String dialog, String answer) throws AppGeneralException {
        Order order = (Order)sessionService.getAsObject(VARS.SESSION_VAR_ORDER);
        if (!answer.equals("") && answer != null && order != null) {
            try {
                try (Connection conn = companyDataSource.getConnection()) {
                    PreparedStatement pstmt = conn.prepareStatement("UPDATE `call_history`\n" +
                            "SET `" + dialog + "` = ? WHERE id = ? ");
                    pstmt.setString(1, answer);
                    pstmt.setLong(2, order.id);
                    pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                throw new AppGeneralException(e.getMessage(), e);
            }
        }
    }

    /**
     * Добавляем в БД id звонка.
     * @throws AppGeneralException
     */
    public void addCallId() throws AppGeneralException {
        Order order = (Order)sessionService.getAsObject(VARS.SESSION_VAR_ORDER);
        long callId = sessionService.getAsLong(ISessionService.CALLID_PARAM);
        try {
            try (Connection conn = companyDataSource.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("UPDATE `call_history`\n" +
                        "SET `call_id` = ? WHERE id = ? ");
                pstmt.setLong(1, callId);
                pstmt.setLong(2, order.id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new AppGeneralException(e.getMessage(), e);
        }
    }

    private String normAni(String phone) {
        if (phone.length() == 11) {
            return phone.substring(1);
        }
        return phone;
    }

    public static class Order {
        private long id = 0;
        private String phone = "";
        private Date orderDate;
        private IGlobalConfigService globalConfigService;
        private BasicDataSource dataSource;
        private ISessionService sessionService;

        public Order(IGlobalConfigService globalConfigService,
                     BasicDataSource dataSource, ISessionService sessionService) {
            this.globalConfigService = globalConfigService;
            this.dataSource = dataSource;
            this.sessionService = sessionService;
        }

        public long getId() {
            return id;
        }

        public String getPhone() {
            return phone;
        }

        public Date getOrderDate() {
            return orderDate;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Order{");
            sb.append("id=").append(id);
            sb.append(", phone='").append(phone).append('\'');
            sb.append(", orderDate=").append(orderDate);
            sb.append('}');
            return sb.toString();
        }
    }
}
