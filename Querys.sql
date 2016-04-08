select 
 p.ID "ID_Protocolo",
 p.DATAPROTOCOLO "DATA",
 p.OBSERVACOES "Observações",
 p.RETORNO "Retorno",
e.ID "ID_Empresa",
 e.CNPJ "CNPJ",
 e.RAZAOSOCIAL "Razão_Social",
 e.SEGMENTO "Segmento",
 d.ID "ID_Documento",
 d.DOCUMENTO "Documento",
 d.TIPO,
 r.ID "ID_Requerente",
 r.CPF "CPF",
 r.NOME "Nome_Requerente",
 r.PROFISSAO "Profissão"
  from protocolo p 
  inner join empresa e on p.EMPRESA = e.ID
  inner join REQUERENTE r on p.REQUERENTE = r.ID
  inner join DOCUMENTOS d on p.ID = d.PROTOCOLO