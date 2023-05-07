# Dynamic REST
Dynamic REST. Rest accept a JQL string, and return set of fields. REST perform a JQL search and return JSON with the selected fields. The result (issues) output without limitation on the number of displays.

#### Before you begin.<br>
Install the [Atlassian Plugin SDK](https://developer.atlassian.com/server/framework/atlassian-sdk/), you'll also need the following: <br>
Java SE Development Kit (JDK) 8 or AdoptOpenJDK 8 <br>
Your JAVA_HOME variable set <br>

#### Before first run <br>
JIRA plugin use maven installing inside pligin package so you need to change path to your Maven directory in your IDE <br>

Path to Maven in MacOS 

usr/local/Cellar/atlassian-plugin-sdk/8.2.7/libexec/apache-maven-3.5.4 <br>

![Maven settings](images/maven_settings.png)

Create template Jira plugin from SDK:

#### `atlas-create-jira-plugin`

You also need to install several plugin modules:
1. Web section / 30
2. Web item / 25
3. Webwork plugin / 31

#### `atlas-create-jira-plugin-module`

Web section won't appear on UI until you define at least one web-item in the section.

Add custom Web section and Web item link.

Insert data in fields
![Plugin view](images/insert_data.png)

Get JSON from input
![Plugin view](images/rab.png)

Full documentation is always available at:

https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK


