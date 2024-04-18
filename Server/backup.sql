-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: MuscleForge
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `MuscleForge`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `MuscleForge` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `MuscleForge`;

--
-- Table structure for table `exercises`
--

DROP TABLE IF EXISTS `exercises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercises` (
  `name` varchar(50) NOT NULL,
  `muscle_group` varchar(50) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `idx_muscle_group` (`muscle_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercises`
--

LOCK TABLES `exercises` WRITE;
/*!40000 ALTER TABLE `exercises` DISABLE KEYS */;
INSERT INTO `exercises` VALUES ('Back Extension','Back','Using a Back Extension bench, lower your upper body down and then extend your back. Works the lower back.'),('Back Squat','Legs','With a barbell on your back, squat down and get up. Works the quads.'),('Barbell Curl','Biceps','Gripping a barbell with a supinated grip, curl your arms. Works the biceps.'),('Barbell Shrug','Back','Standing up with a barbell on your hands more than shoulder width apart, do a shrug movement. Works the traps.'),('Bench Press','Chest','Lying face up on a bench, lower the barbell to your chest and push it up. Works the chest.'),('Bent Over Row','Back','Standing up and bent over, row the barbell. Works the upper back and lats.'),('Cable Curl','Biceps','Gripping a cable bar with a supinated grip, curl your arms. Works the biceps.'),('Cable Row','Back','Sitting down on a cable machine, row the bar. Can be used with a variety of grips. Works the upper back.'),('Chin Up','Back','Using a supinated grip, pull yourself up toward the bar. Works the upper back, lats, and biceps.'),('Crunch','Core','Lying face up on the floor with your knees bent, crunch up using your abs. Works the abs.'),('Cycling','Cardio','Self explanatory.'),('Deadlift','Back','Standing up, squat down and pick up the barbell from the floor using your hands. Works the lower back and the spinal erectors.'),('Decline Bench Press','Chest','Like the Bench Press, but with a decline. Works the lower chest.'),('Decline Dumbbell Bench Press','Chest','Like the Dumbbell Bench Press, but with a decline. Works the lower chest.'),('Dumbbell Bench Press','Chest','Like the Bench Press, but with dumbbels. Works the chest.'),('Dumbbell Curl','Biceps','Gripping dumbbels in your hands, curl your arms. Works the biceps.'),('Dumbbell Fly','Chest','Lying face up on a bench, hold the dumbbells in a cross position and bring them together in a hug-like motion. Works the chest.'),('Dumbbell Row','Back','Supported by a bench, row the dumbbell. Works the upper back and lats.'),('EZ Bar Curl','Biceps','Gripping an EZ bar with a supinated grip, curl your arms. Works the biceps.'),('Face Pull','Shoulders','Using a rope atatched to a cable machine, hold it in front of you and row it back until your elbows are at the side of your head. Works the posterior delts.'),('French Press','Triceps','Sitting up on a bench, hold a bar over your head. Bend your elbows and push the weight up again. Works the triceps.'),('Front Raise','Shoulders','Holding a barbell or dumbbels in front of you, raise them until they\'re at eye level. Works the front delts.'),('Front Squat','Legs','With a barbell on your clavicles, squat down and get up. Works the quads.'),('Hack Squat','Legs','Using a Hack Squat machine, squat down and get up. Works the quads.'),('Hammer Curl','Biceps','Gropping dumbbells with a neutral grip, curl your arms. Works the biceps, brachialis, and brachioradialis'),('Hip Thrust','Legs','Lying face up with a barbell on your hip and your back on a support, thrust your hip up. Works the glutes.'),('Incline Bench Press','Chest','Like the Bench Press, but with an incline. Works the upper chest.'),('Incline Dumbbell Bench Press','Chest','Like the Dumbbell Bench Press, but with an incline. Works the upper chest.'),('Lat Pulldown','Back','Sitting down on a vertical cable machine, pull the bar toward you. Can be used with a variety of grips. Works the and lats.'),('Lateral Raise','Shoulders','Gripping dumbbels with your hands at the side of your body, raise them until they\'re above your clavicle line and lower. Works the side delts.'),('Leg Curl','Legs','Using a Leg Curl machine, curl your legs. Works the hamstrings.'),('Leg Extension','Legs','Using a Leg Extension machine, extend your legs and \"kick\" the weight up. Works the quads.'),('Leg Press','Legs','Using a Leg Press machine, press the weight with your legs. Works the quads.'),('Lu Raise','Shoulders','Gripping weight with your hands at the side of your body, raise unntil they\'re above your head. Works the side delts and traps.'),('Lunge','Legs','With a barbell or dumbbells, lunge forward with one leg and get up. Works the quads.'),('Military Press','Shoulders','With a barbell on your clavicles, push it up above your head with your arms. Works the front delts.'),('Overhead Dumbbell Press','Shoulders','Like a Military Press but with dumbbells. Works the front delts.'),('Plank','Core','Hold yourself in a push up position with your elbows. Works the whole core.'),('Preacher Curl','Biceps','Using a preacher bench, hold a barbell or EZ bar and curl it. Works the biceps.'),('Pull Up','Back','Using a pronated grip, pull yourself up toward the bar. Works the upper back and lats.'),('Pullover','Back','Using a weight or a vertical cable machine, pull the weight over. Works the lats.'),('Push Press','Shoulders','Like a Military Press, but with momentum. Works the front delts.'),('Push Up','Chest','Lie face down on the ground and push yourself up with your arms. Works the chest.'),('Reverse Barbell Curl','Biceps','Gripping a barbell with a pronated grip, curl your arms. Works the brachialis and brachioradialis.'),('Reverse Cable Curl','Biceps','Gripping a cable bar with a pronated grip, curl your arms. Works the biceps.'),('Reverse Dumbbell Curl','Biceps','Gripping dumbbels in your hands, curl your arms leading with your fists. Works the brachialis and brachioradialis.'),('Reverse EZ Barr Curl','Biceps','Gripping an EZ bar with a pronated grip, curl your arms. Works the brachialis and brachioradialis.'),('Reverse Fly','Shoulders','Bent over with dumbbels on your hands, raise them sideways.'),('Romanian Deadlift','Legs','With a very slight bend in your knees, pick up a barbell from the ground and lower it again. Works the hamstrings.'),('Running','Cardio','Self explanatory.'),('SkullCrusher','Triceps','Lying face up on a bench, hold a bar over your head. Bend your elbows and push the weight up again. Works the triceps.'),('Triceps Dip','Triceps','In parallel bars or rings, lower yourself down and push yourself up. Works the triceps.'),('Triceps Extension','Triceps','Using a cable machine, hold the bar over your head and extend your triceps upwards. Works the triceps.'),('Triceps Kickback','Triceps','Supported by a bench, kick the dumbbell or cable back. Works the triceps.'),('Triceps Pushdown','Triceps','Using a cable machine, hold the bar in front of you and extend your triceps downwards. Works the triceps.'),('Upright Row','Back','Standing up with a barbell on your hands shoulder width apart, bring the barbell to your chin. Works the traps.'),('Weighted Dip','Chest','In parallel bars or rings, lower yourself down and push yourself up. Works the chest.');
/*!40000 ALTER TABLE `exercises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `email` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('a','ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-18 19:29:59
