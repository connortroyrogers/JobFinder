package project1;

/**
 * @author Connor Rogers, Aaryana Rajanala, Austin Gineris, Nick Emerson
 * @date 4/30/21
 * C212 Final Project
 */

import java.io.Serializable;
//class to represent Employer
import java.util.ArrayList;

class Employer implements Serializable {

  //name of the Employer
  private String name;
  //Employer username
  private String username;
  //Employer password
  private String password;
  //Job postings of Employer
  private ArrayList<Job> jobs;
  //new Employer(inName, inUsername, inPassword);
  public Employer(String inName, String inUsername, String inPassword) {
    this.name = inName;
    this.username = inUsername;
    this.password = inPassword;
    this.jobs = new ArrayList<Job>();
  }
  public Employer(String name, String username, String password, ArrayList<Job> jobs){
    this.name = name;
    this.username = username;
    this.password = password;
    this.jobs = new ArrayList<Job>();
  }

/**
 * method to add job 
 * @param name input String, is name 
 * @param description input String, is description 
 */
  public void addJob(String name, String description, String location){
    Job newJob = new Job(name, description, this, location);
    jobs.add(newJob);
  }

/**
 * method to view jobs 
 * @return output String
 */
  public String viewJobs() {
    return jobs.toString();
  }

/**
 * method to update job 
 * @param name input String, is name 
 * @param description input String, is description 
 */
  public void updateJob(String jobName, String newName, String newDescription) {
    for(int i = 0; i < jobs.size(); i++){
      if(jobs.get(i).getName().equals(jobName)){
        jobs.get(i).setName(newName);
        jobs.get(i).setDescription(newDescription);
      }
    }
  }

/**
 * method to view job seekers 
 * @return output String
 */
  public String viewJobSeekers() {
    
    String jobSeekers = "";
    for(int i = 0; i < jobs.size(); i++){
      Job checkJob = jobs.get(i);
      jobSeekers += checkJob.getName() + ": \n";
      for(int j = 0; j < checkJob.getPeopleAppliedList().size(); j++){
        jobSeekers += checkJob.getPeopleApplied().toString() + "\n";
      }
    }
    return jobSeekers;
  }

/**
* method to update status
* @param seeker input JobSeeker, is seeker
*/
  public void updateStatus(JobSeeker seeker, Job job, String newStatus){
    for(int i = 0; i < jobs.size(); i++){
      if(job.getName().equals(jobs.get(i).getName())){
        jobs.get(i).getStatus().replace(seeker, newStatus);
      }
    }
  }

/**
 * method to get name 
 * @return output String
 */
  public String getName() {
    return this.name;
  }

/**
 * method to set name 
 */
  public void setName(String name){
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
 */
  public void setUsername(String username){
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
 */
  public void setPassword(String password){
    this.password = password;
  }

/**
 * method to get jobs 
 * @return output ArrayList<Job>
 */
  public ArrayList<Job> getJobs() {
    return this.jobs;
  }

/**
 * method to set jobs 
 */
  public void setJobs(ArrayList<Job> jobs){
    this.jobs = jobs;
  }

}