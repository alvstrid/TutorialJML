# TutorialJML

About:
This is an example of how you can use a containerized version of OpenJML to test your Java code with a Jenkins pipeline.
This setup is configured to test both the RAC and ESC commands. 

Prequisites: 
- A local installation of Docker on your machine

Steps:
- Run the docker-compose file
- Access the containerized Jenkins service and configure your account
- Create a new pipeline and add the repository link to it

Expected results:
- In the pipeline the RAC mode will pass successfully
- The ESC mode will fail
