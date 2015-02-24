/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

/**
 *
 * @author Jinkal
 */
public class DataBean {
        private String name;
        private String stu_id;
        private String percentage;
       
        public void setName(String name) {
                this.name = name;
        }
       
        public String getName() {
                return name;
        }

        public void setPercentage(String occupation) {
                this.percentage = occupation;
        }

        public String getPercentage() {
                return this.percentage;
        }

        public void setStu_id(String place) {
                this.stu_id = place;
        }

        public String getStu_id() {
                return this.stu_id;
        }

        
}

