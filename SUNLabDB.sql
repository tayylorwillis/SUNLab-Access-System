-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 23, 2024 at 11:59 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SUNLabDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `AccessRecords`
--

CREATE TABLE `AccessRecords` (
  `recordId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `accessTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `action` enum('entry','exit') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `AccessRecords`
--

INSERT INTO `AccessRecords` (`recordId`, `userId`, `accessTime`, `action`) VALUES
(1, 5, '2024-09-23 00:17:22', 'entry'),
(2, 7, '2024-09-23 00:17:22', 'exit'),
(3, 6, '2018-09-05 00:17:02', 'exit'),
(4, 1000000, '2023-09-07 00:55:50', 'exit'),
(5, 1000001, '2024-09-23 00:57:38', 'entry');

-- --------------------------------------------------------

--
-- Table structure for table `Admins`
--

CREATE TABLE `Admins` (
  `adminId` int(11) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `userId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status` varchar(20) DEFAULT 'active',
  `userType` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`userId`, `name`, `createdAt`, `status`, `userType`) VALUES
(5, 'Taylor Willis', '2024-09-23 00:15:37', 'active', 'student'),
(6, 'Luke Ehrenzeller', '2024-09-23 19:56:46', 'suspended', 'student'),
(7, 'Bob Lance', '2022-09-14 00:14:56', 'active', 'faculty'),
(1000000, 'Mike Lewis', '2021-09-09 00:54:41', 'active', 'staff'),
(1000001, 'Jack Black', '2024-09-23 00:57:25', 'active', 'janitor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AccessRecords`
--
ALTER TABLE `AccessRecords`
  ADD PRIMARY KEY (`recordId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `Admins`
--
ALTER TABLE `Admins`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AccessRecords`
--
ALTER TABLE `AccessRecords`
  MODIFY `recordId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `Admins`
--
ALTER TABLE `Admins`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000002;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AccessRecords`
--
ALTER TABLE `AccessRecords`
  ADD CONSTRAINT `accessrecords_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `Users` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
