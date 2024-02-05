# USE CASE: <25> <Report Population Information>

## CHARACTERISTIC INFORMATION

### Goal in Context

As a data analyst, I want to extract and present the population of people, people living in cities, and people not living in cities in each country, facilitating continental population distribution analyses.
### Scope

Organization
### Level

Primary

### Preconditions

We need to have database and must know about SQL. 

### Success End Condition

Getting good output and a good report is produced to organization. 

### Failed End Condition

Output cannot be gotten because of database issues so that report cannot be produced.

### Primary Actor

Data Analyst

### Trigger

Working for the organization that requires population information.

## MAIN SUCCESS SCENARIO

1. Data analyst requests report about population of people, peopl living in cities and people not living in cities in each country.
2. System extract from database according to the request. 
3. System displays result. 

## EXTENSIONS

2a. Request and data in system do not match so it will show as null.
	2a1. Checking both request and data in system and fix the wrong one to be right.

## SCHEDULE

2/2/2024
