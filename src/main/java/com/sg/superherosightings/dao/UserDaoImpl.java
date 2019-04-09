/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsPersistenceException;
import sg.thecodetasticfour.superherosightingsgroup.dao.SuperheroSightingsUserDao;
import sg.thecodetasticfour.superherosightingsgroup.dto.Organization;
import sg.thecodetasticfour.superherosightingsgroup.dto.User;

/**
 *
 * @author blake
 */
public class UserDaoImpl implements SuperheroSightingsUserDao {

    private static final String SQL_INSERT_USER
            = "INSERT INTO Users "
            + "(UserName, UserPassword, FirstName, LastName, Email, isAdmin)"
            + "values (?, ?,?,?,?,?);";

    private static final String SQL_SELECT_USER
            = "SELECT * FROM Users where UserID = ?;";

    private static final String SQL_SELECT_ALL_USERS
            = "SELECT * FROM Users;";

    private static final String SQL_UPDATE_USER
            = "UPDATE Users set "
            + "UserName = ?,UserPassword = ?,FirstName = ?,LastName = ?  "
            + "Email = ?, isEnabled = ? "
            + "where UserID = ?;";

    private static final String SQL_DELETE_USER
            = "DELETE FROM Users "
            + "where userID = ?;";

    private static final String SQL_INSERT_AUTHORITY
            = "INSERT INTO Authorities "
            + "(username, authority) "
            + "values (?, ?)";

    private static final String SQL_DELETE_AUTHORITY
            = "DELETE from Authorities where AuthorityID = ?; ";

    private static final String SQL_SELECT_AUTHORITIES_FOR_USER
            = "SELECT a.* "
            + "FROM Authorities a "
            + "JOIN Users u on u.Username = a.Username "
            + "where u.Username = ?";

    private static final String SQL_SELECT_USERS_BY_ORGANIZATION_ID
            = "SELECT user.* "
            + "FROM Users user "
            + "JOIN OrganizationAdmins oa on user.UserID = oa.UserID "
            + "where oa.OrganizationID = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User createUser(User user) throws SuperheroSightingsPersistenceException {
        jdbcTemplate.update(SQL_INSERT_USER,
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getIsEnabled());
        int userId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        user.setUserId(userId);

        //Get user organization bridge table
//        List<Organization> theUserOrg = user.getUserOrganizations();
//        for (Organization org : theUserOrg) {
//       jdbcTemplate.update(SQL_INSERT_ORGANIZATION_ADMINS_FOR_USER,
//        }
        // now insert user's roles
        List<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY,
                    user.getUserName(),
                    authority);
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() throws SuperheroSightingsPersistenceException {
    return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());    
    }

    @Override
    public void updateUser(User user) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(String username) throws SuperheroSightingsPersistenceException {
        // first delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITY, username);
        // second delete the user
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }

    @Override
    public List<User> findUsersForOrganization(Organization organization) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserByUsername(String username) throws SuperheroSightingsPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {

            User u = new User();

            u.setUserId(rs.getInt("UserID"));
            u.setUserName(rs.getString("UserName"));
            u.setUserPassword(rs.getString("UserPassword"));
            u.setFirstName(rs.getString("FirstName"));
            u.setLastName(rs.getString("LastName"));
            u.setEmail(rs.getString("Email"));
            u.setIsEnabled(rs.getBoolean("isEnabled"));

            return u;
        }
    }

}
