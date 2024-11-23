CREATE DATABASE IF NOT EXISTS gestao_estoque;

CREATE TABLE IF NOT EXISTS `estoque` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `produto` varchar(45) DEFAULT NULL,
  `tipo_produto` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_produto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `produtos_cadastrados` (
  `id_produto_cadastrado` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_produto_cadastrado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `estoque` (`id_produto`, `produto`, `tipo_produto`, `quantidade`) VALUES
(4, 'Desinfetante', 2, 3),
(9, 'Água Sanitária', 2, 3),
(10, 'Farinha', 0, 5),
(11, 'Coca Lata', 1, 100),
(12, 'Sprite Lata', 1, 50),
(13, 'Pacote Salsicha 500g', 0,
