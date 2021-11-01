<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![CRM][CRM-shield]][CRM-url]
[![Ironhack][Ironhack-shield]][Ironhack-url]
[![Santander][Santander-shield]][Santander-url]

<!-- PROJECT LOGO -->
![Logo](Server/src/main/resources/images/teapot-09.png)

<br />
<p align="center">

  <h3 align="center">CRM</h3>

  <p align="center">
    CRM project created by tEapot! team:
    <br />
    <a href="https://github.com/natyfromwonderland"><strong>Natalia Shilyaeva</strong></a>
    ·
    <a href="https://github.com/Mat-Poreda"><strong>Mateusz Poreda</strong></a>
    ·
    <a href="https://github.com/MigNeves"><strong>Miguel Neves</strong></a>
    ·
    <a href="https://github.com/patrykwieteska"><strong>Patryk Wieteska</strong></a>
  </p>




<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#class-diagram">Class Diagram</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#issues">Known Issues</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This is a microservice reiteration of a [project of CRM application](https://github.com/EN-IH-WDPT-JUN21/DeadCodersSociety-ClubRickyMartin-Stage2-Homework-3)  which allow user to manage workflow of acquiring *leads* and convert them to sale *opportunities*.

### Class Diagram
![Class Diagram](Server/src/main/resources/images/microservices diagram.jpg)

### Built With

* [IntelliJ](https://www.jetbrains.com/idea/)
* [Java](https://www.java.com/en/)




<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/EN-IH-WDPT-JUN21/tEapot-Microservices-CRM.git
   ```
2. Build a new project in IntelliJ by File -> New -> Project from Version Control
3. Paste the copied URL and follow instructions.
4. Project is based on H2 Database, so no database configuration is required.
5. If projects are not recognised as Maven project -> right click on POM file and click "Add as a Maven project"
6. Run the application.


<!-- USAGE EXAMPLES -->
## Usage
<p>
Project contains several services. Run all of them by starting the main class of every service.
We recommend starting from the Server.
This application works on a API input, so every command should be sent as a HTTP Request with valid parameters and/or body.

</p>




<!-- ROADMAP -->
## Roadmap
This project is still in early development. Future functionalities are yet to be determined.

## Issues
See the [open issues](https://github.com/EN-IH-WDPT-JUN21/tEapot-Microservices-CRM/issues) for a list of known issues.



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Project Link: [https://github.com/EN-IH-WDPT-JUN21/tEapot-Microservices-CRM](https://github.com/EN-IH-WDPT-JUN21/tEapot-Microservices-CRM)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* [Code breaking is welcome. If you'll be able to break this application please let us know!](https://github.com/orgs/EN-IH-WDPT-JUN21/teams/teapot)
* Test all the features! You may find something special!





<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/static/v1?label=Team&message=DeadCodersSociety&color=brightgreen&
[contributors-url]: https://github.com/orgs/EN-IH-WDPT-JUN21/teams/teapot
[CRM-Shield]: https://img.shields.io/static/v1?label=Project&message=CRM&color=yellowgreen&
[CRM-url]: https://en.wikipedia.org/wiki/Customer_relationship_management
[ironhack-shield]: https://img.shields.io/static/v1?label=Bootcamp&message=Ironhack&color=blue&
[ironhack-url]: https://www.ironhack.com/en
[Santander-shield]: https://img.shields.io/static/v1?label=SponsoredBy&message=Santander&color=red&
[Santander-url]: https://www.becas-santander.com/en/index.html
[IntelliJ-shield]: https://img.shields.io/static/v1?label=IDE&message=IntelliJ&color=red&
[IntelliJ-url]: https://www.jetbrains.com/idea/
