/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

/**
 *
 * @author Jinkal
 */
//import Server_Client.Faculty_Home;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.chetty.reporting.beans.DataBean;

public class DataBeanMaker {
        public ArrayList<DataBean> getDataBeanList(ResultSet rs) {
                ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();
               
//                //dataBeanList.add(produce("Babji, Chetty", "Engineer", "Nuremberg", "Germany"));
//                dataBeanList.add(produce("Albert Einstein", "Engineer", "Ulm"));
//                dataBeanList.add(produce("Alfred Hitchcock", "Movie Director", "London"));
//                dataBeanList.add(produce("Wernher Von Braun", "Rocket Scientist", "Wyrzysk"));
//                dataBeanList.add(produce("Sigmund Freud", "Neurologist", "Pribor"));
//                dataBeanList.add(produce("Mahatma Gandhi", "Lawyer", "Gujarat"));
//                dataBeanList.add(produce("Sachin Tendulkar", "Cricket Player","Mumbai"));
//                dataBeanList.add(produce("Michael Schumacher", "F1 Racer", "Cologne"));
                try {
                while(rs.next())
                {
                    //CustomBean cb=new CustomBean
                            System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3));
                            dataBeanList.add(produce(rs.getString(1),rs.getString(2),rs.getString(3)));
                    //ds_list.add(cb);
                    
                }
            } catch (SQLException ex) {
//                Logger.getLogger(Faculty_Home.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                return dataBeanList;
        }
       
        private DataBean produce(String place,String name, String occupation) {
                DataBean dataBean = new DataBean();
               
                dataBean.setName(name);
                dataBean.setPercentage(occupation);
                dataBean.setStu_id(place);
                //dataBean.setCountry(country);
               
                return dataBean;
        }
}

