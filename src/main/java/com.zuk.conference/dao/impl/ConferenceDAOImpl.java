package com.zuk.conference.dao.impl;

import com.zuk.conference.dao.ConferenceDAO;
import com.zuk.conference.model.Conference;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConferenceDAOImpl extends ConferenceDAO {

    @Override
    public Conference findById(int id) {
        System.out.println("Conference findById  Start");
        Conference conference = null;
        if (con != null) {
            try {
                PreparedStatement pr = getPrepareStatement("SELECT * from CONFERENCE where ID = ?");
                pr.setInt(1, id);
                ResultSet resultSet = pr.executeQuery();

                if (resultSet.next()) {
                    conference = Conference.newBuilder()
                            .setAmount_participant(resultSet.getInt("AMOUNT_PARTICIPANT"))
                            .setCapacity_room(resultSet.getInt("CAPACITY_ROOM"))
                            .setId(resultSet.getInt("ID"))
                            .setName(resultSet.getString("NAME"))
                            .setId_room(resultSet.getInt("ID_ROOM"))
                            .setName_room(resultSet.getString("NAME_ROOM"))
                            .setId_participant(resultSet.getString("ID_PARTICIPANT"))
                            .setDatee(resultSet.getDate("DATEE"))
                            .setTimee(resultSet.getTime("TIMEE"))
                            .build();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conference;
    }

    @Override
    public ArrayList findAll() {
        System.out.println("Conference findAll  Start");
        ArrayList arrayList = new ArrayList();
        String error = "Error:";
        if (con != null) {
            try {
                PreparedStatement pr = getPrepareStatement("SELECT  * from CONFERENCE WHERE DATEE > CURRENT_DATE ORDER BY DATEE ASC, TIMEE ASC");
                ResultSet resultSet = pr.executeQuery();

                while (resultSet.next()){
                    Conference conference = Conference.newBuilder()
                            .setId(resultSet.getInt("ID"))
                            .setName(resultSet.getString("NAME"))
                            .setId_room(resultSet.getInt("ID_ROOM"))
                            .setName_room(resultSet.getString("NAME_ROOM"))
                            .setId_participant(resultSet.getString("id_PARTICIPANT"))
                            .setCapacity_room(resultSet.getInt("CAPACITY_ROOM"))
                            .setAmount_participant(resultSet.getInt("AMOUNT_PARTICIPANT"))
                            .setDatee(resultSet.getDate("DATEE"))
                            .setTimee(resultSet.getTime("TIMEE"))
                            .build();
                    System.out.println(conference.toString());
                    arrayList.add(conference);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                error+="Please try later 1; ";

            } catch (Exception e) {
                e.printStackTrace();
                error+="Please try later 2;  ";

            }
        }
        if(error!="Error:"){
            System.out.println("erro!=null");
            ArrayList arrayError= new ArrayList();
            arrayError.add(error);arrayError.add(error);
            return arrayError;
        }else {
            return arrayList;
        }
    }

    @Override
    public boolean updateIdParticipant(String idParticipant, int amountParticipant, int id) {
        System.out.println("Conference updateIdParicipant  Start");
        if (con != null) {
            try {
                PreparedStatement pr = getPrepareStatement("Update  CONFERENCE set ID_PARTICIPANT = ?, AMOUNT_PARTICIPANT = ? where ID=?");
                pr.setString(1, idParticipant);
                pr.setInt(2,amountParticipant);
                pr.setInt(3,id);
                pr.executeUpdate();
                return true;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateDateAndTime(Conference conference) {
        System.out.println("Conference updateDateAndTime  Start");
        if (con != null) {
            try {
                PreparedStatement pr = getPrepareStatement("UPDATE CONFERENCE SET DATEE = ? , TIMEE= ? WHERE ID = ?;");
                pr.setDate(1,conference.getDatee());
                pr.setTime(2,conference.getTimee());
                pr.setInt(3,conference.getId());
                pr.executeUpdate();
                return true;
            }
            catch (SQLException e) {

                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Conference conference) {
        System.out.println("Conference update  Start");
        if (con != null) {
            try {
                PreparedStatement pr = getPrepareStatement("Update  CONFERENCE set NAME = ?, NAME_ROOM = ?, ID_ROOM = ?, ID_PARTICIPANT = ?,CAPACITY_ROOM = ?, AMOUNT_PARTICIPANT = ?,DATEE = ?,TIMEE = ? where ID=?");
                pr.setString(1,conference.getName());
                pr.setString(2,conference.getName_room());
                pr.setInt(3,conference.getId_room());
                pr.setString(4,conference.getId_participant());
                pr.setInt(5,conference.getCapacity_room());
                pr.setInt(6,conference.getAmount_participant());
                pr.setDate(7,conference.getDatee());
                pr.setTime(8,conference.getTimee());
                pr.setInt(9,conference.getId());
                pr.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(int conferenceId) {
        System.out.println("Conference deleteConference  Start");
        if (con != null) {
                try {
                    PreparedStatement pr = getPrepareStatement("DELETE from CONFERENCE where ID=?");
                    pr.setInt(1,conferenceId);
                    pr.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return false;
    }

    @Override
    public boolean save(Conference conference) {
        System.out.println("Conference save  Start");
        if (con != null) {
                try {
                    PreparedStatement pr = getPrepareStatement("insert into CONFERENCE (NAME,ID_ROOM,NAME_ROOM,CAPACITY_ROOM,AMOUNT_PARTICIPANT,DATEE,TIMEE) values (?,?,?,?,?,?,?)");
                    pr.setString(1,conference.getName());
                    pr.setInt(2,conference.getId());
                    pr.setString(3,conference.getName());
                    pr.setInt(4,conference.getCapacity_room());
                    pr.setInt(5,conference.getAmount_participant());
                    pr.setDate(6,conference.getDatee());
                    pr.setTime(7,conference.getTimee());
                    pr.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return false;
    }


}
