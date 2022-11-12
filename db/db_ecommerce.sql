SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecommerce-project`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
    `id` int(10) NOT NULL,
    `name` varchar(50) NOT NULL,
    `description` text NOT NULL,
    `price` int(20) NOT NULL,
    `category` varchar(50) NOT NULL,
    `company` varchar(20) NOT NULL,
    `color` varchar(10) NOT NULL

    ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `category`, `company`, `color` ) VALUES
                                                                                                  (1, 'bed', 'for your comfort', 50, 'bedroom', 'marcos', 'blue'),
                                                                                                  (2, 'chair', 'for your comfort', 35, 'living room', 'ikea', 'red'),
                                                                                                  (3, 'dinning table', 'for your comfort', 59, 'dinning', 'caressa', 'black'),
                                                                                                  (4, 'bar stool', 'for your comfort', 25, 'office', 'liddy', 'green'),
                                                                                                  (5, 'wooden desk', 'for your comfort', 30, 'kitchen', 'ikea', 'blue'),
                                                                                                  (6, 'wooden desk', 'for your comfort', 30, 'kitchen', 'ikea', 'blue');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE IF NOT EXISTS `cart` (
    `id` int(10) NOT NULL,
    `product` varchar(255) NOT NULL,
    `quantity` int(20) NOT NULL,
    `price` int(20) NOT NULL,
    `subtotal` int(20) NOT NULL

    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `product`, `quantity`, `price`,`subtotal`) VALUES
                                                                         (1, 'dinning table',  5, 59, 295),
                                                                         (2, 'bed',  10, 50, 500),
                                                                         (3, 'chair',  3, 35, 105),
                                                                         (4, 'bar stool',  5, 25, 125),
                                                                         (5, 'wooden desk',  15, 30, 450);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
    `id` int(10) NOT NULL,
    `name` varchar(255) NOT NULL

    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Category`
INSERT INTO `category` (`id`, `name`) VALUES
                                          (1, 'living room'),
                                          (2, 'bedroom'),
                                          (3, 'office'),
                                          (4, 'dinning'),
                                          (5, 'kitchen');


CREATE TABLE IF NOT EXISTS `company` (
    `id` int(10) NOT NULL,
    `name` varchar(255) NOT NULL

    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `company`
INSERT INTO `company` (`id`, `name`) VALUES
                                         (1, 'marcos'),
                                         (2, 'ikea'),
                                         (3, 'liddy'),
                                         (4, 'caressa');

CREATE TABLE IF NOT EXISTS `color` (
    `id` int(10) NOT NULL,
    `type` varchar(255) NOT NULL

    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `color`
INSERT INTO `color` (`id`, `type`) VALUES
                                       (1, 'blue'),
                                       (2, 'black'),
                                       (3, 'red'),
                                       (4, 'white');


--
-- Table structure for table `RegisterToMakePayment`
--

CREATE TABLE IF NOT EXISTS `RegisterToMakePayment` (
                                                      `id` int(10) NOT NULL,
                                                      `user` int(10) NOT NULL,
                                                      `payment` int(10) NOT NULL,
                                                      `status` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `RegisterToMakePayment`
--

INSERT INTO `RegisterToMakePayment` (`id`, `user`, `payment`, `status`) VALUES
                                                                              (1, 2, 1, 'PAYMENT_SUCCESSFUL'),
                                                                              (3, 7, 2, 'INCOMPLETE_PAYMENT'),
                                                                              (4, 6, 1, 'PAYMENT_SUCCESSFUL'),
                                                                              (5, 6, 2, 'INCOMPLETE_PAYMENT'),
                                                                              (6, 5, 1, 'PAYMENT_SUCCESSFUL'),
                                                                              (7, 5, 2, 'PAYMENT_SUCCESSFUL');


--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
                                         `id` int(10) NOT NULL,
                                         `status` varchar(50) NOT NULL,
                                         `description` text NOT NULL,
                                         `amount` int(20) NOT NULL,
                                         `cart` varchar(50) NOT NULL,
                                         `dateTime` datetime NOT NULL,
                                         `user` varchar(20) NOT NULL


) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `status`, `description`, `amount`, `cart`, `dateTime`,`user`) VALUES
                                                                                                  (1, 'SUCCESSFUL', 'payment for a bed', 50, '1', '2022-09-01 12:14:18', 'jamesorban'),
                                                                                                  (2, 'INCOMPLETE', 'payment for a wooden chair', 60, '5', '2022-10-01 12:14:18','orban'),
                                                                                                  (3, 'SUCCESSFUL', 'payment for a bar stool', 20, '9', '2022-08-01 12:14:18','james'),
                                                                                                  (4, 'SUCCESSFUL', 'payment for a dinning table', 35, '10', '2022-11-01 12:14:18', 'doofan'),
                                                                                                  (5, 'SUCCESSFUL', 'payment for a wooden desk', 29, '7', '2022-12-01 12:14:18', 'theresa'),
                                                                                                  (6, 'INCOMPLETE', 'payment for a bedsitter', 75, '2', '2022-11-01 12:14:18','thomas');



--
-- Table structure for table `application_report`
--

CREATE TABLE IF NOT EXISTS `application_report` (
                                                    `id` int(10) NOT NULL,
                                                    `text` text NOT NULL,
                                                    `applicant` int(10) NOT NULL,
                                                    `user` int(10) NOT NULL,
                                                    `dateTime` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `application_report`
--

INSERT INTO `application_report` (`id`, `text`, `applicant`, `user`, `dateTime`) VALUES
                                                                                 (4, 'First comment', 2, 6, '2022-12-01 12:14:18'),
                                                                                 (5, 'Second comment', 2, 5, '2022-12-01 15:34:43'),
                                                                                 (6, 'Third comment', 2, 4, '2022-12-01 17:27:24'),
                                                                                 (9, 'Test comment', 2, 3, '2022-12-09 00:41:49'),
                                                                                 (12, 'Very good, well done !', 6, 5, '2022-12-12 23:15:48');

-- --------------------------------------------------------

--
-- Table structure for table `applicant`
--

CREATE TABLE IF NOT EXISTS `applicant` (
                                       `id` int(10) NOT NULL,
                                       `synodMember` int(10) NOT NULL,
                                       `catalogue` int(10) NOT NULL,
                                       `status` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `applicant`
--

INSERT INTO `applicant` (`id`, `synodMember`, `catalogue`, `status`) VALUES
                                                                         (3, 7, 4, 'DECLINED'),
                                                                         (2, 6, 2, 'IN_REVIEW'),
                                                                         (5, 7, 14, 'IN_REVIEW'),
                                                                         (6, 56, 15, 'IN_REVIEW');

-- --------------------------------------------------------

--
-- Table structure for table `synod`
--

CREATE TABLE IF NOT EXISTS `synod` (
                                            `id` int(10) NOT NULL,
                                            `name` varchar(255) NOT NULL,
                                            `date` date NOT NULL,
                                            `town` varchar(255) NOT NULL,
                                            `country` int(10) NOT NULL,
                                            `closed` tinyint(1) NOT NULL,
                                            `synodCategory` int(10) NOT NULL,
                                            `description` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `synod`
--

INSERT INTO `synod` (`id`, `name`, `date`, `town`, `country`, `closed`, `synodCategory`, `description`) VALUES
                                                                                                            (1, 'International Ministerial Synod', '2022-12-31', 'London', 1, 0, 2, 'A synod for get together of all branches across the world'),
                                                                                                            (2, 'RCCG Youth Synod 2022', '2023-02-14', 'Abuja', 1, 0, 1, 'An international youth synod for the development of youth across the word');

-- --------------------------------------------------------

--
-- Table structure for table `synod_category`
--

CREATE TABLE IF NOT EXISTS `synod_category` (
                                                    `id` int(10) NOT NULL,
                                                    `name` varchar(255) NOT NULL,
                                                    `description` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `synod_category`
--

INSERT INTO `synod_category` (`id`, `name`, `description`) VALUES
                                                                   (1, 'YOUTH', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse venenatis erat nibh, vitae sollicitudin arcu sodales et. Suspendisse pulvinar lacus in nisi hendrerit malesuada. Integer  nunc sit amet, mattis lectus'),
                                                                   (2, 'ALL TIME SYNOD', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse venenatis erat nibh, vitae sollicitudin arcu sodales et. Suspendisse pulvinar lacus in nisi hendrerit malesuada. Integer tincidunt nunc , mattis lectus'),
                                                                   (3, 'RELIGIOUS DOCTRINE', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse venenatis erat nibh, vitae sollicitudin arcu sodales et. Suspendisse pulvinar lacus in nisi hendrerit malesuada. amet, mattis lectus'),
                                                                   (4, 'INTERNATIONAL REPRESENTATIVES', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse venenatis erat nibh, vitae sollicitudin arcu sodales et. Suspendisse pulvinar lacus in nisi hendrerit malesua mattis lectus'),
                                                                   (5, 'PASTORAL SYNOD', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse venenatis erat nibh, vitae sollicitudin arcu sodales et. Suspendisse pulvinar lacus in nisi hendrerit malesuada. Integer accumsan ipsum ac ');

-- --------------------------------------------------------

--
-- Table structure for table `synod_section`
--

CREATE TABLE IF NOT EXISTS `synod_section` (
                                                   `id` int(10) NOT NULL,
                                                   `name` varchar(255) NOT NULL,
                                                   `category` int(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `synod_section`
--

INSERT INTO `synod_section` (`id`, `name`, `category`) VALUES
                                                               (1, 'YOUTH', 1),
                                                               (2, 'PASTORAL', 1),
                                                               (3, 'ALL MEMBERSHIP', 1),
                                                               (4, 'CHOIR', 2),
                                                               (5, 'BIBLICAL SYNOD', 2);

-- --------------------------------------------------------

--
-- Table structure for table `synod_section2_synod`
--

CREATE TABLE IF NOT EXISTS `synod_section2_synod` (
                                                              `synod` int(10) NOT NULL,
                                                              `section` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `synod_section2_synod`
--

INSERT INTO `synod_section2_synod` (`synod`, `section`) VALUES
                                                                         (2, 1),
                                                                         (2, 2),
                                                                         (2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
                                         `id` int(10) NOT NULL,
                                         `name` varchar(255) NOT NULL,
                                         `isoCode` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `name`, `isoCode`) VALUES
                                                    (1, 'NIGERIA', 'NGA'),
                                                    (2, 'ANGOLA', 'ANG'),
                                                    (3, 'EGYPT', 'EGY'),
                                                    (4, 'GHANA', 'GHA'),
                                                    (5, 'CANADA', 'CAD'),
                                                    (6, 'TURKEY', 'TUR'),
                                                    (7, 'GERMANY', 'GER'),
                                                    (8, 'UNITED KINGDOM', 'GBR'),
                                                    (9, 'CHINA', 'CHA'),
                                                    (10, 'SOUTH AFRICA', 'SA');

-- --------------------------------------------------------

--
-- Table structure for table `catalogue`
--

CREATE TABLE IF NOT EXISTS `catalogue` (
                                          `id` int(10) NOT NULL,
                                          `name` varchar(255) NOT NULL,
                                          `description` text NOT NULL,
                                          `createdAt` date NOT NULL,
                                          `file` longblob NOT NULL,
                                          `fileName` varchar(255) NOT NULL,
                                          `user` int(10) NOT NULL,
                                          `synod` int(10) NOT NULL,
                                          `catalogueCategory` int(10) NOT NULL,
                                          `section` int(10) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `catalogue`
--

INSERT INTO `catalogue` (`id`, `name`, `description`, `createdAt`, `file`, `fileName`, `user`, `synod`, `catalogueCategory`, `section`) VALUES
                                                                                                                                       (2, 'Bible Presentation', 'To keep soul and body', '2022-12-12', 0x6161612f616161, 'test.pdf', 6, 2, 1, 1),
                                                                                                                                        (4, 'Youth Spiritual Empowerment', 'for youth growth', '2021-06-09', 0x8001a745 ,'yth.pdf', 56, 2, 5, 3);
-- --------------------------------------------------------

--
-- Table structure for table `catalogue_category`
--

CREATE TABLE IF NOT EXISTS `catalogue_category` (
                                                  `id` int(10) NOT NULL,
                                                  `name` varchar(255) NOT NULL,
                                                  `description` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `catalogue_category`
--

INSERT INTO `catalogue_category` (`id`, `name`, `description`) VALUES
                                                                 (1, 'BIBLICAL WORK', 'Donec id lobortis urna, at fermentum nisl. Nunc non odio accumsan, ornare lectus quis, egestas est. Aenean a orna'),
                                                                 (2, 'YOUTH AND CHILDREN UPBRINGING', 'Donec id lobortis urna, at fermentum nisl. Nunc non odio accumsan, ornare lectus quis, egestas est. Aenean a ornare lacus.'),
                                                                 (3, 'PASTORA WORK', 'Teaches and guides on pastoral representation'),
                                                                 (4, 'DRUMMERS AND CHOIR', 'The important part of a church. Motivates the body to praise and worship God in spirit.'),
                                                                 (5, 'GOD IN HUMAN REPRESENTATION', 'Donec id lobortis urna, at fermentum nisl. Nunc non odio accumsan, ornare lectus quis, egestas est. Aenean a ornare lacus. ex.');

-- --------------------------------------------------------

--
-- Table structure for table `register_to_synod`
--

CREATE TABLE IF NOT EXISTS `register_to_synod` (
                                                      `id` int(10) NOT NULL,
                                                      `user` int(10) NOT NULL,
                                                      `synod` int(10) NOT NULL,
                                                      `status` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `register_to_synod`
--

INSERT INTO `register_to_synod` (`id`, `user`, `synod`, `status`) VALUES
                                                                              (1, 2, 1, 'SYNOD_APPROVED'),
                                                                              (3, 7, 2, 'SYNOD_APPROVED'),
                                                                              (4, 6, 1, 'SYNOD_APPROVED'),
                                                                              (5, 6, 2, 'SYNOD_APPROVED'),
                                                                              (6, 5, 1, 'SYNOD_APPROVED'),
                                                                              (7, 5, 2, 'SYNOD_APPROVED\n'),
                                                                              (10, 9, 2, 'REGISTERED_TO_APP'),
                                                                              (19, 3, 2, 'SYNOD_APPROVED'),
                                                                              (20, 56, 2, 'REGISTERED_TO_APP');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE IF NOT EXISTS `review` (
                                        `id` int(10) NOT NULL,
                                        `registrar` int(10) NOT NULL,
                                        `applicant` int(10) NOT NULL,
                                        `status` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `registrar`, `applicant`, `status`) VALUES
                                                                      (6, 3, 3, 'DECLINED'),
                                                                      (7, 4, 3, 'DECLINED'),
                                                                      (8, 5, 2, 'APPROVED'),
                                                                      (41, 3, 5, 'IN_PROGRESS'),
                                                                      (43, 3, 2, 'IN_PROGRESS'),
                                                                      (49, 5, 6, 'APPROVED');

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE IF NOT EXISTS `title` (
                                       `id` int(10) NOT NULL,
                                       `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`id`, `name`) VALUES
                                       (1, 'PASTOR'),
                                       (2, 'CATECHIST'),
                                       (3, 'BISHOP'),
                                       (4, 'STUDENT'),
                                       (5, 'PROFESSOR'),
                                       (6, 'DIRECTOR'),
                                       (7, 'CLERGY'),
                                       (8, 'OTHERS');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
                                      `id` int(10) NOT NULL,
                                      `name` varchar(255) NOT NULL,
                                      `surname` varchar(255) NOT NULL,
                                      `email` varchar(255) NOT NULL,
                                      `username` varchar(255) NOT NULL,
                                      `password` varchar(255) NOT NULL,
                                      `title` int(10) NOT NULL,
                                      `role` int(10) NOT NULL,
                                      `country` int(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `email`, `username`, `password`, `title`, `role`, `country`) VALUES
                                                                                                              (1, 'Orban', 'Aondowase', 'orbanjames5@gmail.com', 'orbanaondowase', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 5, 1, 1),
                                                                                                              (2, 'James', 'Aondowase', 'james@test.com', 'jamesaondowase', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 5, 2, 1),
                                                                                                              (3, 'Doofan', 'Orban', 'doofan@test.com', 'doofanorban', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 7, 3, 1),
                                                                                                              (4, 'Ngukenger', 'Orban', 'ngukenger@test.com', 'orbankenger', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 6, 3, 1),
                                                                                                              (5, 'Torkuma', 'Orwase', 'torkuma@test.com', 'torkumawase', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 6, 3, 1),
                                                                                                              (6, 'Emmanuel', 'Tester', 'emmanuel@test.com', 'emmy123', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 1, 4, 1),
                                                                                                              (7, 'Torkwase', 'Terwase', 'torkwase@test.com', 'terwade', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 1, 4, 1),
                                                                                                              (9, 'Iorfa', 'Ukaase', 'iorfa@gmail.com', 'iorfa12345', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se', 5, 4, 2),
                                                                                                              (54, 'Mohammmed', 'Mohammed', 'mohammed@sd.com', 'dasdasdasssafdfd', '$2a$10$Wd8Oc4FMwaSwAQfTqw8MPeeSbmAnN/Ryw5v6BKHik7dluftzp5ZZi', 3, 4, 1),
                                                                                                              (56, 'Titus', 'Geruun', 'titus.geruun@gmail.com', 'titus12', '$2a$10$Z9l/4dxdICELs6RHB2UNT.wwqwt3suoNvqu63EsKM5okbY9.9j7xi', 5, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_register2_synod_section`
--

CREATE TABLE IF NOT EXISTS `user_register2_synod_section` (
                                                                       `registrar` int(10) NOT NULL,
                                                                       `section` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_register2_synod_section`
--

INSERT INTO `user_register2_synod_section` (`registrar`, `section`) VALUES
                                                                                       (3, 1),
                                                                                       (5, 1),
                                                                                       (5, 2),
                                                                                       (4, 3),
                                                                                       (5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
                                          `id` int(10) NOT NULL,
                                          `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `name`) VALUES
                                          (1, 'SUPER_ADMIN'),
                                          (2, 'SYNOD_ADMIN'),
                                          (3, 'REGISTRAR'),
                                          (4, 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`id`),
    ADD KEY `category_index` (`category`),
    ADD KEY `company_index` (`company`),
    ADD KEY `color_index` (`color`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `username` (`username`),
    ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `RegisterToMakePayment`
--
ALTER TABLE `RegisterToMakePayment`
    ADD PRIMARY KEY (`id`,`user`,`payment`),
    ADD UNIQUE KEY `id` (`id`),
    ADD UNIQUE KEY `unique_index` (`id`,`user`,`payment`),
    ADD UNIQUE KEY `unique_index2` (`user`,`payment`),
    ADD KEY `conf_register_fk` (`payment`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
    ADD PRIMARY KEY (`id`),
    ADD KEY `customer_index` (`user`),
    ADD KEY `cart_index` (`cart`);

--
-- Indexes for table `applicant`
--
ALTER TABLE `applicant`
    ADD PRIMARY KEY (`id`,`synodMember`,`catalogue`),
    ADD KEY `status` (`status`),
    ADD KEY `apply_catalogue_fk` (`catalogue`),
    ADD KEY `applicant_synodMember_fk` (`synodMember`);

--
-- Indexes for table `synod`
--
ALTER TABLE `synod`
    ADD PRIMARY KEY (`id`),
    ADD KEY `category_index` (`synodCategory`),
    ADD KEY `country_index` (`country`);

--
-- Indexes for table `synod_category`
--
ALTER TABLE `synod_category`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `synod_section`
--
ALTER TABLE `synod_section`
    ADD PRIMARY KEY (`id`),
    ADD KEY `category` (`category`);

--
-- Indexes for table `synod_section2_synod`
--
ALTER TABLE `synod_section2_synod`
    ADD PRIMARY KEY (`synod`,`section`),
    ADD KEY `section_2_synod_synodFK` (`section`);

--
-- Indexes for table `country`
--
ALTER TABLE `country`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `catalogue`
--
ALTER TABLE `catalogue`
    ADD PRIMARY KEY (`id`),
    ADD KEY `conf_index` (`synod`),
    ADD KEY `user_index` (`user`),
    ADD KEY `cat_index` (`catalogueCategory`),
    ADD KEY `section` (`section`);

--
-- Indexes for table `catalogue_category`
--
ALTER TABLE `catalogue_category`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register_to_synod`
--
ALTER TABLE `register_to_synod`
    ADD PRIMARY KEY (`id`,`user`,`synod`),
    ADD UNIQUE KEY `id` (`id`),
    ADD UNIQUE KEY `unique_index` (`id`,`user`,`synod`),
    ADD UNIQUE KEY `unique_index2` (`user`,`synod`),
    ADD KEY `synod_register_fk` (`synod`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `unique_index_review` (`registrar`,`applicant`),
    ADD KEY `registrar` (`registrar`),
    ADD KEY `apply` (`applicant`),
    ADD KEY `status` (`status`),
    ADD KEY `applicant_2` (`applicant`),
    ADD KEY `registrar_2` (`registrar`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
    ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `username` (`username`),
    ADD UNIQUE KEY `email` (`email`),
    ADD KEY `country_index` (`country`),
    ADD KEY `role_index` (`role`),
    ADD KEY `title_index` (`title`);

--
-- Indexes for table `user_register2_synod_section`
--
ALTER TABLE `user_register2_synod_section`
    ADD PRIMARY KEY (`registrar`,`section`),
    ADD KEY `section_fk` (`section`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
    ADD PRIMARY KEY (`id`);


--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `color`
--
ALTER TABLE `color`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `RegisterToMakePayment`
--
ALTER TABLE `RegisterToMakePayment`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `application_report`
--
ALTER TABLE `application_report`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `applicant`
--
ALTER TABLE `applicant`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `synod`
--
ALTER TABLE `synod`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `synod_category`
--
ALTER TABLE `synod_category`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `synod_section`
--
ALTER TABLE `synod_section`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `country`
--
ALTER TABLE `country`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `catalogue`
--
ALTER TABLE `catalogue`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `synod_category`
--
ALTER TABLE `synod_category`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `register_to_synod`
--
ALTER TABLE `register_to_synod`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `title`
--
ALTER TABLE `title`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;


--
-- Constraints for table `product`
--
ALTER TABLE `product`
    ADD CONSTRAINT `product_cat_fk` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `product_comp_fk` FOREIGN KEY (`company`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `product_col_fk` FOREIGN KEY (`color`) REFERENCES `color` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `RegisterToMakePayment`
--
ALTER TABLE `RegisterToMakePayment`
    ADD CONSTRAINT `pay_register_fk` FOREIGN KEY (`payment`) REFERENCES `payment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `user_register_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
    ADD CONSTRAINT `payment_cart_fk` FOREIGN KEY (`cart`) REFERENCES `cart` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `payment_user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- Constraints for table `application_report`
--
ALTER TABLE `application_report`
    ADD CONSTRAINT `comment_app_fk` FOREIGN KEY (`applicant`) REFERENCES `applicant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `comment_user_fk` FOREIGN KEY (`user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `applicant`
--
ALTER TABLE `applicant`
    ADD CONSTRAINT `applicant_synodMember_fk` FOREIGN KEY (`synodMember`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `applicant_catalogue_fk` FOREIGN KEY (`catalogue`) REFERENCES `catalogue` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `synod`
--
ALTER TABLE `synod`
    ADD CONSTRAINT `synod_cat_fk` FOREIGN KEY (`synodCategory`) REFERENCES `synod_category` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `conf_country_fk` FOREIGN KEY (`country`) REFERENCES `country` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `synod_section`
--
ALTER TABLE `synod_section`
    ADD CONSTRAINT `section_category_fk` FOREIGN KEY (`category`) REFERENCES `synod_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ConferenceSection2Conference`
--
ALTER TABLE `synod_section2_synod`
    ADD CONSTRAINT `conference_fk` FOREIGN KEY (`synod`) REFERENCES `synod` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `section_2_synod_synodFK` FOREIGN KEY (`section`) REFERENCES `synod_section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `catalogue`
--
ALTER TABLE `catalogue`
    ADD CONSTRAINT `catalogue_cat_fk` FOREIGN KEY (`catalogueCategory`) REFERENCES `catalogue_category` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `catalogue_synod_fk` FOREIGN KEY (`synod`) REFERENCES `synod` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `catalogue_section_fk` FOREIGN KEY (`section`) REFERENCES `synod_section` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `catalogue_user_fk` FOREIGN KEY (`user`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `register_to_synod`
--
ALTER TABLE `register_to_synod`
    ADD CONSTRAINT `synod_register_fk` FOREIGN KEY (`synod`) REFERENCES `synod` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `user_register_fk` FOREIGN KEY (`user`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
    ADD CONSTRAINT `review_applicant_fk` FOREIGN KEY (`applicant`) REFERENCES `applicant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `review_rg_fk` FOREIGN KEY (`registrar`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
    ADD CONSTRAINT `user_country_fk` FOREIGN KEY (`country`) REFERENCES `Country` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `user_role_fk` FOREIGN KEY (`role`) REFERENCES `user_role` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `user_title_fk` FOREIGN KEY (`title`) REFERENCES `Title` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `user_register2_synod_section`
--
ALTER TABLE `user_register2_synod_section`
    ADD CONSTRAINT `cm_fk` FOREIGN KEY (`registrar`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `section_fk` FOREIGN KEY (`section`) REFERENCES `synod_section` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
