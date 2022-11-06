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
                                                                                                  (5, 'wooden desk', 'for your comfort', 30, 'kitchen', 'ikea', 'blue');

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
    `password` varchar(255) NOT NULL
    ) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `User`
--

INSERT INTO `user` (`id`, `name`, `surname`,`email`, `username`, `password`) VALUES

                                                                                 (1, 'James', 'Orban', 'orbanjames5@gmail.com', 'jamesorban', '2iwjkfhvhs.jaidufyusuiw.e837463'),
                                                                                 (2, 'Aondowase', 'Orban', 'orbanjams4@gmail.com', 'orbanjames', '$2a$10$Ag.0Pg4fDw6TKX6A1w.5860se'),
                                                                                 (3, 'Martin', 'Ande', 'martin@test.com', 'martinande', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se'),
                                                                                 (4, 'Peter', 'Paul', 'ppaul@test.com', 'peterpaul', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se'),
                                                                                 (5, 'Doofan', 'Doofi', 'doofi@test.com', 'doofandoofi', '$2a$10$Ag.0Pg4f7bDW4Y.ZCWB9VOlj7gbAFlNYKgwrDw6TKX6A1w.5860se');
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
-- AUTO_INCREMENT for dumped tables
--

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
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `user`
    MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=57;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
    ADD CONSTRAINT `product_cat_fk` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON UPDATE CASCADE,
    ADD CONSTRAINT `product_comp_fk` FOREIGN KEY (`company`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `product_col_fk` FOREIGN KEY (`color`) REFERENCES `color` (`id`) ON UPDATE CASCADE;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
