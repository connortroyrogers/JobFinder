package project1;

/**
 * @author Connor Rogers, Aaryana Rajanala, Austin Gineris, Nick Emerson
 * @date 4/30/21
 * C212 Final Project
 */
 
import java.io.Serializable;
//Class to represent JobSeekers
import java.util.ArrayList;

public class JobSeeker implements Serializable {

//name of jobseeker
private String name;
//username of jobseeker
private String username;
//password of jobseeker
private String password;
//past jobs of jobseeker
private ArrayList<String> pastJobs;
//skills of jobseeker
private ArrayList<String> skills;
//address of oj
private String address;
//phone number of jobseeker
private String phoneNum;

JobSeeker(String name, String username, String password, ArrayList<String> pastJobs, ArrayList<String> skills, String address, String phoneNum){
  this.name = name;
  this.username = username;
  this.password = password;
  this.pastJobs = pastJobs;
  this.skills = skills;
  this.address = address;
  this.phoneNum = phoneNum;
}

JobSeeker(String name, String username, String password, String address, String phoneNum){
  this.name = name;
  this.username = username;
  this.password = password;
  this.pastJobs = new ArrayList<String>();
  this.skills = new ArrayList<String>();
  this.address = address;
  this.phoneNum = phoneNum;
}

public String viewProfile() {
  return "Viewing Profile";

}
/**
 * method to update profile 
 * @param name input String, is name 
 * @param username input String, is username 
 * @param password input String, is password 
 * @param String input public, is string 
 * @param phoneNum input String, is phone num 

 */
public void updateProfile(String name, String username, String password, String address, String phoneNum) {
  this.name = name;
  this.username = username;
  this.password = password;
  this.address = address;
  this.phoneNum = phoneNum;
}
/**
 * method to update past jobs 
 * @param job input String, is job 
 */
public void updatePastJobs(String job) {
  pastJobs.add(job);
}
/**
 * method to update skills 
 * @param skill input String, is skill 
 */
public void updateSkills(String skill) {
  skills.add(skill);
}
/**
 * method to browse jobs 
 * @return output String
 */
public String browseJobs() {
  return "";
}
/**
 * method to view status 
 * @return output String
 */
public String viewStatus(ArrayList<Employer> employers) {
  String outputString = "";
  for (Employer i : employers) {
    for (Job job : i.getJobs()) {
    	boolean present = false;
    	String tempStatus = "";
    	for (JobSeeker j : job.getStatus().keySet()) { 
    		if (this.equals(j)) {
    			present = true; 
    			tempStatus = job.getStatus().get(j);
    		}
    		
    	}
      if (present) {
        outputString = outputString + "Job: " + job.getName() + " Status: " + tempStatus + "\n";
      }
    }
  }
  return outputString;
}

public void applyToJob(Job job){
  job.getStatus().put(this, "pending");
}

//getters and setters
/**
 * method to get name 
 * @return output String
 */
public String getName() {
  return this.name;
}
/**
 * method to set name 
 * @param name input String, is name 
 */
public void setName(String name) {
  this.name = name;
}
/**
 * method to get username 
 * @return output String
 */
public String getUsername() {
  return this.username;
}
/**
 * method to set username 
 * @param username input String, is username 
 */
public void setUsername(String username) {
  this.username = username;
}
/**
 * method to get password 
 * @return output String
 */
public String getPassword() {
  return this.password;
}
/**
 * method to set password 
 * @param password input String, is password 
 */
public void setPassword(String password) {
  this.password = password;
}
/**
 * method to get past jobs 
 * @return output ArrayList<String>
 */
public ArrayList<String> getPastJobs() {
  return pastJobs;
}
/**
 * method to set past jobs 
 * @param pastJobs input ArrayList<String>, is past jobs 
 */
public void setPastJobs(ArrayList<String> pastJobs) {
  this.pastJobs = pastJobs;
}
/**
 * method to get skills 
 * @return output ArrayList<String>
 */
public ArrayList<String> getSkills() {
  return this.skills;
}
/**
 * method to set skills 
 * @param skills input ArrayList<String>, is skills 
 */
public void setSkills(ArrayList<String> skills) {
  this.skills = skills;
}
/**
 * method to get address 
 * @return output ArrayList<String>
 */
public String getAddress() {
  return address;
}
/**
 * method to set address 
 */
public void setAddress(String address) {
  this.address = address;
}
/**
 * method to get phone number
 * @return output String
 */
public String getPhoneNum() {
  return phoneNum;
}
/**
 * method to set phone number
 */
public void setPhoneNum(String phoneNum) {
  this.phoneNum = phoneNum;
}

/**
 * creates a String representation of a JobSeeker object
 * @return output String
 */
public String toString(){
        if(this.pastJobs == null && this.skills == null){
            return "Name: " + this.name + "\n"
                    + "Address: " + this.address + "\n"
                    + "Phone Number: " + this.phoneNum;
        } else if (this.pastJobs == null && this.skills != null){
            return "Name: " + this.name + "\n"
                    + "Skills: " + this.skills.toString() + "\n"
                    + "Address: " + this.address + "\n"
                    + "Phone Number: " + this.phoneNum;
        }else if (this.pastJobs != null && this.skills == null){
            return "Name: " + this.name + "\n"
                    + "Past Jobs: " + this.pastJobs.toString() + "\n"
                    + "Address: " + this.address + "\n"
                    + "Phone Number: " + this.phoneNum;
        }else{
            return "Name:" + this.name + "\n"
                    + "Past Jobs: " + this.pastJobs.toString() + "\n"
                    + "Skills: " + this.skills.toString() + "\n"
                    + "Address: " + this.address + "\n"
                    + "Phone Number: " + this.phoneNum;
        }
    }

/**
 * method add a skill to a JobSeeker
 */
    public void addSkill(String skill) {
      skills.add(skill);
    }
/**
 * method to add a past job to a JobSeeker
 */
    public void addPastJob(String pastJob) {
      pastJobs.add(pastJob);
    }
/**
 * method to check if two JobSeeker objects are equal
 * @return output boolean
 */
    public boolean equals(JobSeeker jobSeeker) {
    	if (this.name.equals(jobSeeker.getName()) &&
    		this.username.equals(jobSeeker.getUsername()) &&
    		this.password.equals(jobSeeker.getPassword()) &&
    		this.address.equals(jobSeeker.getAddress()) &&
    		this.phoneNum.equals(jobSeeker.getPhoneNum())) { return true; }
    	else { return false; }
    }

}