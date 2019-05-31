"url checker.zip" file has two mandatory files in it which is urlchecker.jar and urls.csv and should belong to same folder

1. urls.csv
This is a csv file in which the very first column have all URL to test to, this need to be updated as per test urls.(do not change this file name)

2. urlchecker.jar
This is an executable jar file which performs our task to get urls from very first column from "urls.csv" file and further generates the "outputfile.csv" file which has first column as input urls and 2nd column as its response.

3. "outputfile.csv" 
This output file is being created on successful execution only, so as per number of input URLs the time creating this file may be more that guessed. (1000 URL took more that 3 hrs to create this output file with BigD urls).

4. There is a log file named "connection" is generated on execution with all the logs required to debug any issues.


Other Notes :
There is option of creating a property file named as "config.properties" which can have 
1. Input csv file name say "mycsvfile.csv" and need to specify property key as "fileName"
2. And need to specify column key as "columnNumber" (which starts with index 0) say 11;

Note : Test results with this tool on BigD urls looks good, but in case of any issues please write me at anand.pandey@trantorinc.com(email/skype)
