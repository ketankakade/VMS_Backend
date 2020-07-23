CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contactNo` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `idProof` varchar(255) NOT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `placeOfVisit` varchar(255) NOT NULL,
  `reasonForVisit` varchar(255) NOT NULL,
  `visitorType` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;