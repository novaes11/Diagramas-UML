package src.POO.SistemaPagamento;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para lidar com timestamps e formatação de data/hora.
 */
public class GerenciadorTimestamp {

    // Define o fuso horário padrão (pega o do sistema operacional)
    private static final ZoneId FUSO_HORARIO_PADRAO = ZoneId.systemDefault();

    // Define o formato "limpo" padrão
    private static final String FORMATO_PADRAO = "dd/MM/yyyy HH:mm:ss";

    /**
     * Construtor privado para impedir que esta classe utilitária seja instanciada.
     */
    private GerenciadorTimestamp() {
        // Esta classe não deve ser instanciada
    }

    /**
     * Pega o timestamp atual (Instant) no padrão UTC.
     * @return um objeto Instant representando o momento atual.
     */
    public static Instant getTimestampAtual() {
        return Instant.now();
    }

    /**
     * Pega o timestamp atual e o formata de forma legível
     * (ex: "25/10/2025 14:30:15").
     * Usa o fuso horário do sistema e o formato "dd/MM/yyyy HH:mm:ss".
     *
     * @return Uma String com a data e hora formatada.
     */
    public static String getTimestampFormatado() {
        // Chama a versão mais completa do método com os valores padrão
        return getTimestampFormatado(FORMATO_PADRAO);
    }

    /**
     * Pega o timestamp atual e o formata de forma legível
     * usando um padrão (formato) específico.
     * Usa o fuso horário do sistema.
     *
     * @param padrao O padrão de formatação (ex: "yyyy-MM-dd" ou "HH:mm").
     * @return Uma String com a data e hora formatada.
     */
    public static String getTimestampFormatado(String padrao) {
        // 1. Pega o timestamp atual
        Instant timestamp = Instant.now();

        // 2. Cria o formatador com o padrão desejado
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padrao);

        // 3. Converte o Instant (UTC) para o fuso horário local
        ZonedDateTime dataHoraComFuso = timestamp.atZone(FUSO_HORARIO_PADRAO);

        // 4. Formata e retorna a string
        return formatador.format(dataHoraComFuso);
    }
}