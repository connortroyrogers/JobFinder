package project1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Connor Rogers, Aaryana Rajanala, Austin Gineris, Nick Emerson
 * @date 4/30/21
 * C212 Final Project
 */

public class Main {
	
	static ArrayList<Employer> employers;
	static ArrayList<JobSeeker> jobSeekers;

/**
* main runner for the Job Search Application
* contains user registration, login, and menus for other options
*/
	public static void main(String[] args) throws IOException {
		employers = new ArrayList<Employer>();
		jobSeekers = new ArrayList<JobSeeker>();
    deserialize();
		System.out.println("~ Welcome to Job Search App ~");
		
		boolean finalInputComplete = false;
		Scanner in = new Scanner(System.in);
		
		
		
		while (!finalInputComplete) {
			System.out.print("Enter J for jobseeker mode\nE for employer mode\nEnter Q to quit\n");
			String choice1 = in.nextLine();
      if(choice1.equals("Q")){
        finalInputComplete = true;
        continue;
      }
			if (!(choice1.equals("J") || choice1.equals("E") || choice1.equals("Q"))) {
        System.out.println("Invalid input");
        continue;
      }
			System.out.println("To register, enter R. To login, enter L. To quit, enter Q.");
			String choice2 = in.nextLine();
			if (!(choice2.equals("L") || choice2.equals("R") || choice2.equals("Q"))) {
        System.out.println("Invalid input");
        continue;
      }
      if(choice2.equals("Q")){
        finalInputComplete = true;
        continue;
      }
			System.out.print("Enter username: ");
			String inUsername = in.nextLine();
			System.out.print("Enter password: ");
			String inPassword = in.nextLine();
			
			boolean userNameTaken = false;
			boolean loginCorrect = false;

			switch (choice1) {
			case "J":
				for (JobSeeker i : jobSeekers) {
					if (i.getUsername().equals(inUsername)) { userNameTaken = true; }
          if (i.getPassword().equals(inPassword)) {
            loginCorrect = true;
          }
				}
				break;
			case "E":
				for (Employer i : employers) {
					if (i.getUsername().equals(inUsername)) { userNameTaken = true; }
          if (i.getPassword().equals(inPassword)) {
            loginCorrect = true;
          }
				}
				break;
			}
			
			switch (choice2) {
			case "R":
        if (userNameTaken) {
          System.out.println("Username taken");
          continue;
        } else {
          System.out.println("Please enter full name: ");
          String inName = in.nextLine();
          switch (choice1) {
            case "J":
              System.out.println("Please enter address");
              String inAddress = in.nextLine();
              System.out.println("Please enter phone #");
              String inPhoneNumber = in.nextLine();
              JobSeeker inJobSeeker = new JobSeeker(inName, inUsername, inPassword, inAddress, inPhoneNumber);
              jobSeekers.add(inJobSeeker);
              System.out.println("Job seeker created. Add skills/past employment after login");
              System.out.println("Returning to login menu...");
              continue;
            case "E":
              Employer inEmployer = new Employer(inName, inUsername, inPassword);
              employers.add(inEmployer);
              System.out.println("Employer created.");
              System.out.println("Returning to login menu...");
              continue;
          }
        }
				break;
			case "L":
				//login menu
        int empIndex = 0;
        for(int i = 0; i < employers.size(); i++)
          if(employers.get(i).getUsername().equals(inUsername))
            empIndex = i;
				if (userNameTaken && loginCorrect) {
					String input = "";
					//employer perspective
					if(choice1.equals("E")){
						while(!input.equals("8")){
							System.out.println("Please choose an option:");
							System.out.println("1. Create new job");
							System.out.println("2. Update job");
							System.out.println("3. View jobs");
							System.out.println("4. Browse job seekers who have applied to your jobs");
              System.out.println("5. Browse all job seekers");
              System.out.println("6. Update job seeker status (accept/reject)");
              System.out.println("7. Search for job seekers by skills");
							System.out.println("8. Sign out");
							System.out.println("9. Delete account");
							input = in.nextLine();

              for(int i = 0; i < employers.size(); i++)
                if(employers.get(i).getUsername().equals(inUsername))
                  empIndex = i;
	
							if(input.equals("1")){
                System.out.println("Please enter the name of the job you would like to create");
                String jobName = in.nextLine();
                System.out.println("Please enter the description for the job you are creating");
                String jobDesc = in.nextLine();
                System.out.println("Please enter the location for the job you are creating");
                String location = in.nextLine();
                employers.get(empIndex).addJob(jobName, jobDesc, location);
							}
							else if(input.equals("2")){
                System.out.println("Please enter the name of the job you would like to update");
                String jobName = in.nextLine();
                System.out.println("Please enter the new name for the job you are updating");
                String newJobName = in.nextLine();
                System.out.println("Please enter the new description for the job you are updating");
                String newJobDesc = in.nextLine();
                boolean jobExists = false;
                for(Employer emp : employers){
                  for(Job job : emp.getJobs()){
                    if(job.getName().equals(newJobName))
                      jobExists = true;
                  }
                }
                if(jobExists)
                 System.out.println("Sorry, a job with that name already exists");
                else
                  employers.get(empIndex).updateJob(jobName, newJobName, newJobDesc);
							}
							else if(input.equals("3")){
                System.out.println(employers.get(empIndex).viewJobs());
							}
							else if(input.equals("4")){
                for(int i = 0; i < employers.get(empIndex).getJobs().size(); i++){
                  System.out.println("Job: " + employers.get(empIndex).getJobs().get(i).getName());
                  for(int j = 0; j < employers.get(empIndex).getJobs().get(i).getStatus().keySet().toArray().length; j++)
                    System.out.println(employers.get(empIndex).getJobs().get(i).getStatus().keySet().toArray()[j]);
                  System.out.println();
                }
							}
              else if(input.equals("5")){
                for(JobSeeker seeker : jobSeekers)
                System.out.println(seeker.toString());
              }
              else if(input.equals("6")){
                System.out.println("Please enter the full name of the person you would like to change the status of");
                String name = in.nextLine();
                System.out.println("Please enter the name of the job you are changing status for");
                String jobName = in.nextLine();
                System.out.println("Please enter A to accept a job seeker and R to reject");
                String newStatus = in.nextLine();
                int jobSeekerNum = -1;
                for(int i = 0; i < jobSeekers.size(); i++){
                  if(jobSeekers.get(i).getName().equals(name)){
                    jobSeekerNum = i;
                  }
                }
                int jobNum = -1;
                for(int i = 0; i < employers.get(empIndex).getJobs().size(); i++){
                  if(employers.get(empIndex).getJobs().get(i).getName().equals(jobName))
                  jobNum = i;
                }

                if(jobSeekerNum == -1)
                  System.out.println("Sorry, this job seeker doesn't exist");
                else if(jobNum == -1)
                  System.out.println("Sorry, this job doesn't exist");
                else{
                  if(newStatus.equals("A"))
                  employers.get(empIndex).getJobs().get(jobNum).getStatus().replace(jobSeekers.get(jobSeekerNum), "Accepted");
                  else
                  employers.get(empIndex).getJobs().get(jobNum).getStatus().replace(jobSeekers.get(jobSeekerNum), "Rejected");
                }
              }
              else if(input.equals("7")){
                System.out.println("Please enter the skill you are searching for");
                String searchSkill = in.nextLine();
                String answer = "";
                for(JobSeeker seeker : jobSeekers){
                  for(String skill : seeker.getSkills()){
                    if(searchSkill.contains(skill) || skill.contains(searchSkill)){
                      answer += seeker.getName() + "\n";
                    }
                  }
                }
                System.out.println("Here are the job seekers with this skill:");
                System.out.println(answer);
              }
							else if(input.equals("8")){
                
							}
							else if(input.equals("9")){
                employers.remove(empIndex);
                input = "8";
							}
							else {
								System.out.println("Invalid input");
							}
	
						}
					}
					//job seeker perspective
					if(choice1.equals("J")){
              int jobSeekerIndex = 0;
              for(int i = 0; i < jobSeekers.size(); i++)
                if(jobSeekers.get(i).getUsername().equals(inUsername))
                  jobSeekerIndex = i;
						while(!input.equals("8")){
							System.out.println("Please choose an option:");
							System.out.println("1. View profile");
							System.out.println("2. Update profile");
              System.out.println("3. Add skill to profile");
              System.out.println("4. Add past job to profile");
							System.out.println("5. Browse jobs");
							System.out.println("6. View job status");
              System.out.println("7. Apply to job");
							System.out.println("8. Sign out");
							System.out.println("9. Delete account");
							input = in.nextLine();
              	
							if(input.equals("1")){
                System.out.println("Profile:\n" + jobSeekers.get(jobSeekerIndex).toString());
							}
							else if(input.equals("2")){
                System.out.println("Please enter the new name:");
                String name = in.nextLine();
                System.out.println("Please enter the new username:");
                String username = in.nextLine();
                System.out.println("Please enter the new password:");
                String password = in.nextLine();
                System.out.println("Please enter the new address:");
                String address = in.nextLine();
                System.out.println("Please enter the new phone number:");
                String phoneNumber = in.nextLine();
                jobSeekers.get(jobSeekerIndex).updateProfile(name, username,
                password, address, phoneNumber);
							}
              else if(input.equals("3")){
                System.out.println("Enter skill:\n");
                String tempSkill = in.nextLine();
                jobSeekers.get(jobSeekerIndex).addSkill(tempSkill);
              }
              else if(input.equals("4")){
                System.out.println("Enter past job:\n");
                String tempPastJob = in.nextLine();
                jobSeekers.get(jobSeekerIndex).addPastJob(tempPastJob);
              }
							else if(input.equals("5")){
                for(int i = 0; i < employers.size(); i++){
                  for(int j = 0; j < employers.get(i).getJobs().size(); j++)
                    if(!employers.get(i).getJobs().get(j).getStatus().containsValue("Accepted"))
                      System.out.println(employers.get(i).getJobs().get(j));
                }
                System.out.println();
							}
							else if(input.equals("6")){
                System.out.println(jobSeekers.get(jobSeekerIndex).viewStatus(employers));
							}
              else if(input.equals("7")){
                System.out.println("Please enter the name of the job you would like to apply for");
                String toApply = in.nextLine();
                int empNum = -1;
                int jobNum = -1;
                for(int i = 0; i < employers.size(); i++){
                  System.out.println("Employer: " + employers.get(i).getName());
                  for(int j = 0; j < employers.get(i).getJobs().size(); j++){
                    if(employers.get(i).getJobs().get(j).getName().equals(toApply)){
                      empNum = i;
                      jobNum = j;
                    }
                  }
                }
                if(empNum == -1 || jobNum == -1) {
                  System.out.println("Sorry, that job does not exist.");
                }
                else{
                  jobSeekers.get(jobSeekerIndex).applyToJob(employers.get(empNum).getJobs().get(jobNum));
                }
              }
							else if(input.equals("8")){
	
							}
							else if(input.equals("9")){
                jobSeekers.remove(jobSeekerIndex);
                input = "8";
							}
							else {
								System.out.println("Invalid input");
							}
	
						}
						break;
					}

				} else if (!userNameTaken){
          System.out.println("No username in the system, returning to menu...");
        } else {
          System.out.println("Incorrect credentials, returning to menu...");
        }
			}

		}
    serialize();
    in.close();
	}
	
  /**
  * uses object serialization to allow for state resistance to run the program
  */
	public static void serialize() throws IOException {
		try {
			FileOutputStream fileOut = new FileOutputStream("employers.ser");
			FileOutputStream fileOut1 = new FileOutputStream("jobSeekers.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			ObjectOutputStream out1 = new ObjectOutputStream(fileOut1);
			out.writeObject(employers);
			out1.writeObject(jobSeekers);
			out1.close();
			out.close();
			fileOut1.close();
			fileOut.close();
			
		} catch (Exception e) {

		}
	}

/**
* deserializes information whenever the program is started
*/
  public static void deserialize() throws IOException {
      try {
        FileInputStream fileIn = new FileInputStream("employers.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        employers = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
        FileInputStream fileIn1 = new FileInputStream("jobSeekers.ser");
        ObjectInputStream in1 = new ObjectInputStream(fileIn1);
        jobSeekers = (ArrayList) in1.readObject();
        in1.close();
        fileIn1.close();
      } catch (Exception e) { }
  }

}
