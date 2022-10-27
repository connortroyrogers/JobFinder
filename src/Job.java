package project1;

/**
 * @author Connor Rogers, Aaryana Rajanala, Austin Gineris, Nick Emerson
 * @date 4/30/21
 * C212 Final Project
 */

import java.io.Serializable;
//Class to represent Job posting
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Job implements Serializable {

  //Name of the job
  private String name;
  //description of the job
  private String description;
  //Employer who posted the job
  private Employer employer;
  //location of the job
  private String location;
  //JobSeeker ArrayList of people who applied
  private ArrayList<JobSeeker> peopleApplied;
  //Map of JobSeekers and their respective statuses
  private HashMap<JobSeeker, String> status;

  //Constructors
  public Job(String name, String description, Employer employer, ArrayList<JobSeeker> peopleApplied, HashMap<JobSeeker, String> status, String location) {
    this.name = name;
    this.description = description;
    this.employer = employer;
    this.peopleApplied = peopleApplied;
    this.status = status;
    this.location = location;
  }

  public Job(String name, String description, Employer employer, String location) {
    this.name = name;
    this.description = description;
    this.employer = employer;
    this.peopleApplied = new ArrayList<JobSeeker>();
    this.status = new HashMap<JobSeeker, String>();
    this.location = location;
  }

  //get name variable
/**
 * method to get name 
 * @return output String
 */
  public String getName(){
    return name;
  }
  //set name variable
/**
 * method to set name 
 * @param name input String, is name 

 */
  public void setName(String name){
    this.name = name;
  }
  //get description variable
/**
 * method to get description 
 * @return output String
 */
  public String getDescription(){
    return description;
  }
  //sets description variable
/**
 * method to set description 
 * @param description input String, is description 

 */
  public void setDescription(String description){
    this.description = description;
  }
  //gets employer variable
/**
 * method to get employer 
 * @return output String
 */
  public Employer getEmployer(){
    return employer;
  }
  //sets employer variable
/**
 * method to set employer 
 * @param employer input Employer, is employer 

 */
  public void setEmployer(Employer employer){
    this.employer = employer;
  }
  //gets status map
/**
 * method to get status as a String
 * @return output String
 */
  public String getStatusString(){
        return status.toString();
  }
/**
 * method to get status 
 * @return output Map<JobSeeker,String>
 */
  public Map<JobSeeker, String> getStatus(){
    return status;
  }

  //sets the status
/**
 * method to set status 
 * @param jobseeker input JobSeeker, is jobseeker 
 * @param string input String, is string 
 */
  public void setStatus(JobSeeker jobseeker, String string){
    status.put(jobseeker, string);
  }
  /**
 * method to get location 
 * @return output String
 */
  public String getLocation(){
    return this.location;
  }
/**
 * method to set location
 */
  public void setLocation(String location){
    this.location = location;
  }
/**
 * method to get people applied 
 * @return output String
 */
  public String getPeopleApplied(){
    String applied = "";
    for(int i = 0; i < peopleApplied.size(); i++){
        applied += peopleApplied.get(i) +" \n";
      }
    return applied;
  }
  /**
 * method to get people applied 
 * @return output ArrayList
 */
  public ArrayList<JobSeeker> getPeopleAppliedList(){
    return peopleApplied;
  }
  //sets the ArrayList of people applied
/**
 * method to set people applied 
 * @param jobseeker input JobSeeker, is jobseeker 
 */
  public void setPeopleApplied(JobSeeker jobseeker){
    peopleApplied.add(jobseeker);
  }
/**
* creates a String representation of a Job
* @return output String
*/
  public String toString() {
    return "Job Name: " + name + "\n" +
            "Job Description: " + description + "\n" +
            "Employer: " + employer.getName() + "\n" +
            "Location: " + location + "\n";
  }
}