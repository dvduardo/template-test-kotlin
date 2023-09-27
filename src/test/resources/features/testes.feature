#language:pt
Funcionalidade: Testar novos endpoints

  Cenario: Realizar um get
    Quando GET no endpoint "GET_USERS"
    Entao validar resposta como 200

  Cenario: criar novo usuario com payload aleatorio
    Quando solicitar cadastro de usuario com payload "create_user" alterado
    Entao validar resposta como 201
    Entao salva id da resposta
    Entao validar mensagem de resposta
      | message | Cadastro realizado com sucesso |

  Cenario: excluir usuario da base pelo id
    Quando solicitar exclusao do usuario anteriormente criado
    Entao validar resposta como 200
    Entao validar mensagem de resposta
      | message | Registro excluído com sucesso |

  Cenario: tentar excluir usuario com id inexistente
    Quando solicitar exclusao do usuario com id "1234"
    Entao validar mensagem de resposta
      | message | Nenhum registro excluído |

  Cenario: tentar criar um usuario com email duplicado
    Quando solicitar cadastro de usuario com payload "create_user"
    Entao validar resposta como 400
    Entao validar mensagem de resposta
      | message | Este email já está sendo usado |