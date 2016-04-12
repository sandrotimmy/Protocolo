select 
 p.ID "ID_Prot",
 p.DATAPROTOCOLO "DATA",
 p.OBSERVACOES "Observações",
 p.RETORNO "Retorno",
e.ID "ID_Emp",
 e.CNPJ "CNPJ",
 e.RAZAOSOCIAL "Razão_Social",
 e.SEGMENTO "Segmento",
 d.ID "ID_Doc",
 d.DOCUMENTO "Documento",
 d.TIPO "Tipo",
 d.COMPLEMENTO "Complemento",
 r.ID "ID_Req",
 r.CPF "CPF",
 r.NOME "Nome_Req",
 r.PROFISSAO "Profissão"
  from protocolo p 
  join empresa e on p.EMPRESA = e.ID
  join REQUERENTE r on p.REQUERENTE = r.ID
  join DOCUMENTOS d on p.ID = d.PROTOCOLO