package br.luciano.codetickets.writers;

import br.luciano.codetickets.models.Movimentacao;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MovimentacaoWriterConfig {

    private String sql =
            "insert into tb_movimentacao (nome, cpf, agencia, conta, valor, mes_referencia, hora_importacao) values (:nome, :cpf, :agencia, :conta, :valor, :mesReferencia, :horaImportacao)";

    @Autowired
    private DataSource dataSource;

    @Bean
    public ItemWriter<Movimentacao> movimentacaoWriter() {
        return new JdbcBatchItemWriterBuilder<Movimentacao>()
                .dataSource(dataSource)
                .sql(sql)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
