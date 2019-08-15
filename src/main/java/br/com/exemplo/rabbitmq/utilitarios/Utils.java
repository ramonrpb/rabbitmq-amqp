package br.com.exemplo.rabbitmq.utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.exemplo.rabbitmq.dto.ValidacaoDTO;

public class Utils {

	public static void montaResposta(ValidacaoDTO validacaoDTO, StringBuilder resposta, boolean isCompativel, String regex) {
		resposta.append("{\"match\": ");
		resposta.append(isCompativel);
		resposta.append(", \"regex\": ");
		resposta.append("\"");
		resposta.append(regex);
		resposta.append("\", ");
		resposta.append("\"correlationId\": ");
		resposta.append("\"");
		resposta.append(validacaoDTO.getCorrelationId());
		resposta.append("\"}");
	}
	
	public static boolean isMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
        return false;
        }
    }  
}
