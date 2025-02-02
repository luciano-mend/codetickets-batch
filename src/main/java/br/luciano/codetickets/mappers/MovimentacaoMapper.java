package br.luciano.codetickets.mappers;

import br.luciano.codetickets.models.Movimentacao;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovimentacaoMapper implements FieldSetMapper<Movimentacao> {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    @Override
    public Movimentacao mapFieldSet(FieldSet fieldSet) throws BindException {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setNome(fieldSet.readString("nome"));
        movimentacao.setCpf(fieldSet.readString("cpf"));
        movimentacao.setAgencia(fieldSet.readString("agencia"));
        movimentacao.setConta(fieldSet.readString("conta"));
        movimentacao.setValor(fieldSet.readDouble("valor"));

        String mesReferenciaString = fieldSet.readString("mesReferencia");
        LocalDate mesReferencia = LocalDate.parse("01/" + mesReferenciaString, dateFormatter);
        movimentacao.setMesReferencia(mesReferencia);

        movimentacao.setHoraImportacao(LocalDateTime.now());

        return movimentacao;
    }
}
