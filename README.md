# PROJECT STATUS (Java 8)

- Master branch [![Build Status](https://img.shields.io/travis/manolodd/jglobaldatetime/master.svg)](https://travis-ci.org/manolodd/jglobaldatetime)

- Development branch [![Build Status](https://img.shields.io/travis/manolodd/jglobaldatetime/development.svg)](https://travis-ci.org/manolodd/jglobaldatetime?branch=development)

# THE PROJECT

<b>JGlobalDateTime</b> is a Java library that eases the comparation, transmission, conversion and storage of zoned datetime formats.

![JGlobalDateTime logo](https://raw.githubusercontent.com/manolodd/jglobaldatetime/master/logo/jglobaldatetime-logo.jpg)

# LICENSE

## Current version:
 
- <b>JGlobalDateTime 2.1:</b> Apache Software License 2.0.

## Previous versions:

- <b>GlobalDateTime 2.0:</b> Apache Software License 2.0.
- <b>JGlobalDateTime <2.0:</b> Closed source. Do not look for them.

# PEOPLE BEHIND JGLOBALDATETIME

## Author:
    
 - Manuel Domínguez-Dorado - <ingeniero@ManoloDominguez.com>
  

# THIRD-PARTY COMPONENTS

JGlobalDateTime uses third-party components each one of them having its own OSS license. License compatibility has been taken into account to allow JGlobalDateTime be released under its current OSS licence. They are:

- slf4j-api - MIT - https://www.slf4j.org/
- slf4j-simple - MIT - https://www.slf4j.org/

Thanks folks!

# COMPILING FROM SOURCES

You can download latest compiled stable releases from the releases section of this repository. However, if you want to test new features (please, do it and give feedback), you will need to compile the project from sources. Follow these steps:

 - Clone the JGlobalDateTime repo: 
```console
git clone https://github.com/manolodd/jglobaldatetime.git
```
 - Compile the code and obtain a binary jar including all you need (you will need to install Ant before):
```console
cd jglobaldatetime
ant jglobaldatetime-binary-fat-release
```
 - The jar file will be located in binary-fat-release/jglobaldatetime directory.
```console
cd binary-fat-release/jglobaldatetime
```
 - Copy the library jar file and use it in your project.
```console
cp JGlobalDateTime-bin-v{YourVersion}.jar /wherever/you/want
```
 - It is not necessary but if you want to see JGlobalDateTime in action, the library contains a Main class. You can run it!
```console
java -jar JGlobalDateTime-bin-v{YourVersion}.jar
```

# I NEED AN EXAMPLE

Don't worry. You will find one in the sourcecode tree [here](https://github.com/manolodd/jglobaldatetime/blob/master/src/com/manolodominguez/jglobaldatetime/example/JGlobalDateTimeExample.java). 


# HOW TO CONTRIBUTE

JGlobalDateTime is opensource software. I encourage you to modify it as much as possible; but I would like you to send this modifications back and, hence, became an JGlobalDateTime contributor. In this way, all the people will benefit from them as you are doing downloading and using JGlobalDateTime now.

If you want to contribute to JGlobalDateTime project, follow these instructions:

 - Log in to your GitHub account.
 - Look for JGlobalDateTime project.
 - Create a fork of JGlobalDateTime (Development branch) in your own GitHub repository.
 - Clone your JGlobalDateTime repository to your PC or laptop.
 - Create a branch in your local cloned GIT repository. I recommend to name this branch as "jglobaldatetime-festureyouaredeveloping" or something similar.
 - Do all modifications on this branch, file additions or deletions, modifications, commits...
 - Push your modifications to your remote github JGlobalDateTime repository.
 - Go again to yout GitHub account, choose your JGlobalDateTime repository and then your "jglobaldatetime-festureyouaredeveloping" branch (since the previous step, this branch should be there) and click on the green button at the left. This will guide you to make a pull request (send your modifications from your own JGlobalDateTime repository to JGlobalDateTime main repository from where you did your fork at the beginning). Choose the development branch of JGlobalDateTime as base branch to merge.
 - That's all; I will have your contribution and I will try to merge it into the development branch of JGlobalDateTime. Please, comment your contribution as much as possible; I have to be able to understand your contribution.

This is a very easy process. However, if it is very difficult to you, simply send me all your modifications (sources, documentation...) to:

jglobaldatetime@manolodominguez.com

And I will do my best to understand them and, somewhat, include your contribution into the project.

REMEMBER!!!! all your contributions have to be compatible with Apache Software License 2.0 and you have to own all rights on them.

#### Thanks for contributing.
