# Mainspring-Automation-Framework

   # Automation Framework Mainspring [Risks]
   
   This project involves automating the Risks module in mainspring application.
   
   ## Installation
   
   Extract the zip folder and update to maven project and run the pom.xml or run profiles from cmd using maven commands .
   Requirements - JDK8 and maven 3.6.0+ version
   
   
   ## IMPORTANT NOTE:
   Please create a new browser profile [Firefox]recommended and login to mainspring application once and then run the code.
   Name the profile as "Automation",else change the profile name in line No-576 in setUpDriver method in BaseUtils class.
   
   ## Technologies used 
   
   *The testScript is written in Java,with clean Syntax and comments with hard code free .
   *Locators are stored under locators.xml and xml parsing is used to fetch the locators from the file.
   *Extent Report and logging is implemented with Screenshots .
   *Data Driven testing is followed where the testdata is fetched from the excel file ,mainspring.xlsx.
   *POM model is implemented where All the common methods are placed inside the BaseUtils class and Page/Tab specific methods are stored in respective Util classes.
   *Folder structure is maintained for better readability and maintenence.
   *common properties and paths are stored in config.properties and module.properties file.
   
   Some of the implemented tools are Selenium,TestNG,Extent reporting using relavantcodes api,APache POI ,maven ,junit etc.
   
   ## RoadMap
   
   Dividing the task among the group .
   Creating a testSuite and carry out manual testing.
   SetUp Environment For Automation .
  
  ## Authors and Contributors
  
  *Faiz Ahmed Siddiqh K -Owner of this Test Framework,BaseUtils class.Author of Homepage and Details Tab in Risks module . 
  *Nazneen Shaikh-Co    -Author and owner of Mitigation & Contingencies,Cost Benefit Analysis,Attachments tabs.
  *Gourab Dutta         -Co-Author and owner of Previous Versions,Activity Log tabs.
  *Swapnadip Haldar     -Co-Author and owner of Linked Cards,Attachments and Comments tabs.
  
  ## Contribute
  
  A lot of hard work,Planning and contribution from the team has gone to this project.We would love to have your feedback and appreciation.
  
  Kfasid@protonmail.com
   
