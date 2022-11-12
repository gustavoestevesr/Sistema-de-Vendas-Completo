## Sistema de Vendas Completo

Programa em Java para manter um cadastro de produtos e suas respectivas vendas. O aplicativo deve apresentar também alguns relatórios de controle para o usuário. 

Algumas regras sobre o software:
- Um produto deve ter os seguintes atributos: código, nome, valor e quantidade em estoque.
- Uma venda deve ter os seguintes atributos: data da venda, o produto vendido e a quantidade vendida.
- As vendas são feitas sempre para um único produto.
- Para realizar a venda, sempre verifique se o produto a ser vendido existe em estoque.

A estrutura mínima de menus/funcionalidades que o aplicativo deve ter é a seguinte:
- 1 Incluir produto
- 2 Consultar produto
- 3 Listagem de produtos
- 4 Vendas por período – detalhado
- 5 Realizar venda
- 0 Sair

Os dados constantes da listagem de produtos são:
Cabeçalho:
  - Título.
Detalhe:
  - Código do produto, nome do produto, valor unitário e quantidade em estoque.
Rodapé:
  - Valor médio, máximo e mínimo dos produtos.

Os dados constantes do relatório de vendas - detalhado são:
Cabeçalho:
  - Título.
  - Período de emissão.
Detalhe:
  - Data da venda, nome do produto, quantidade, valor unitário e valor total.
Rodapé:
  - Valor médio das vendas para aquele período
