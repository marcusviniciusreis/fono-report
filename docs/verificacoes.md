# Verificações do fono-report

### Separação dos históricos
Foram criados dois pacientes com o mesmo nome e registros diferentes.
A consulta retornou somente o atendimento de cada paciente.

Resultado: comportamento esperado confirmado.

### Exibição do histórico
Foi conferida a apresentação de:
- Nome do paciente.
- Data da sessão no formato brasileiro.
- Situação do atendimento.
- Observação.

Resultado: comportamento esperado confirmado.

### Cadastro com encaminhamento médico
Ao responder "n", o cadastro não solicita o nome do médico.
Ao responder "s", solicita o nome, permitindo deixá-lo vazio.

Resultado: comportamento esperado confirmado.

### Observação opcional em registro previsto

Foi registrado um atendimento PREVISTO com observação vazia.
O sistema aceitou o registro.
Resultado: comportamento esperado confirmado.

### Verificação pendente

- Confirmar que um atendimento REALIZADO com observação vazia
  é rejeitado.