import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário “João” da lista
        funcionarios.removeIf(func -> func.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("3.3 - Lista de funcionários:");
        for (Funcionario func : funcionarios) {
            String dataNascimento = func.getDataNascimento().format(formatter);
            String salarioFormatado = String.format("%,.2f", func.getSalario()).replace(".", "X").replace(",", ".").replace("X", ",");
            System.out.println(func.getNome() + ", Data de Nascimento: " + dataNascimento + ", Salário: " + salarioFormatado + ", Função: " + func.getFuncao());
        }

        // 3.4 - Aumentar salário em 10%
        for (Funcionario func : funcionarios) {
            BigDecimal novoSalario = func.getSalario().multiply(BigDecimal.valueOf(1.10));
            func.setSalario(novoSalario);
        }

        // 3.5 - Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\n3.6 - agrupar por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario func : entry.getValue()) {
                System.out.println("\t" + func.getNome());
            }
        }

        // 3.8 - Funcionários com aniversário nos meses 10 e 12
        System.out.println("\n3.8 - Funcionários que fazem aniversário em outubro e dezembro:");
        for (Funcionario func : funcionarios) {
            Month mes = func.getDataNascimento().getMonth();
            if (mes == Month.OCTOBER || mes == Month.DECEMBER) {
                System.out.println(func.getNome());
            }
        }

        // 3.9 - Funcionário com maior idade
        Funcionario funcionarioMaisVelho = Collections.max(funcionarios, Comparator.comparingInt(func -> Period.between(func.getDataNascimento(), LocalDate.now()).getYears()));
        int idadeMaisVelho = Period.between(funcionarioMaisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("\n3.9 - Funcionário com a maior idade: " + funcionarioMaisVelho.getNome() + ", Idade: " + idadeMaisVelho);

        // 3.10 - Funcionários em ordem alfabética
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\n3.10 - Funcionários em ordem alfabética:");
        for (Funcionario func : funcionariosOrdenados) {
            System.out.println(func.getNome());
        }

        // 3.11 - Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n3.11 - Total dos salários: " + String.format("%,.2f", totalSalarios).replace(".", "X").replace(",", ".").replace("X", ","));

        // 3.12 - Salários mínimos por funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\n3.12 - Quantidade de salários mínimos por funcionário:");
        for (Funcionario func : funcionarios) {
            BigDecimal salariosMinimos = func.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(func.getNome() + ": " + salariosMinimos + " salários mínimos");
        }
    }
}