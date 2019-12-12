# DNA variant RESTful API

A RESTful application build with Spring for showing DNA variant data of 8 UMCs. 

## Prerequisites
- MySQL (or another database) should be installed

## Getting started

- Clone or fork this repository
- Your own database should be connected and running, you can change the settings in ```application.properties``` file
- Line 24 in the data file ```(src > main > resources > data.sql)``` should point at your locally saved consensus file (found under downloads)
- Run the project

## Usage

### Show data

```/all``` Shows all the variants, paginated

```/all?page={number}&size={number}``` Define your own page and size

```/chromosome/{number}``` Shows variants from a specified chromosome

### Filter data
The data can be filtered according to UMCs and their classification
```/classification?amc=benign&erasmus=likely_pathogenic```

### Health
```/actuator/health``` shows a health page with info about the database and a custom message for demonstration purposes

## Contact
email: a.h.c.lugtenberg@st.hanze.nl
