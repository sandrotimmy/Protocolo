select 
 p.ID "ID_Protocolo",
 p.DATAPROTOCOLO "DATA",
 p.OBSERVACOES "Observa��es",
 p.RETORNO "Retorno",
e.ID "ID_Empresa",
 e.CNPJ "CNPJ",
 e.RAZAOSOCIAL "Raz�o_Social",
 e.SEGMENTO "Segmento",
 d.ID "ID_Documento",
 d.DOCUMENTO "Documento",
 d.TIPO,
 r.ID "ID_Requerente",
 r.CPF "CPF",
 r.NOME "Nome_Requerente",
 r.PROFISSAO "Profiss�o"
  from protocolo p 
  inner join empresa e on p.EMPRESA = e.ID
  inner join REQUERENTE r on p.REQUERENTE = r.ID
  inner join DOCUMENTOS d on p.ID = d.PROTOCOLO