package view;

import java.util.List;
import java.util.Scanner;

import util.conexaoBD;

// As entidades e DAOs agora estão no pacote raiz (sem DAOs/)
import model.direct;
import model.postagem;
import model.usuario;
import DAO.usuarioDAO;
import DAO.postagemDAO;
import DAO.curtidaDAO;
import DAO.directDAO;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Agora criamos uma conexão única para todos os DAOs
        Connection connection = conexaoBD.getConnection();

        usuarioDAO usuarioDAO = new usuarioDAO(connection);
        postagemDAO postagemDAO = new postagemDAO(connection);
        curtidaDAO curtidaDAO = new curtidaDAO(connection);
        directDAO directDAO = new directDAO(connection);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Criar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Atualizar usuário");
            System.out.println("4. Excluir usuário");
            System.out.println("5. Criar model.postagem");
            System.out.println("6. Listar postagens de um usuário");
            System.out.println("7. Excluir model.postagem");
            System.out.println("8. Curtir model.postagem");
            System.out.println("9. Descurtir model.postagem");
            System.out.println("10. Mostrar quantidade de curtidas");
            System.out.println("11. Enviar Direct");
            System.out.println("12. Listar Directs entre dois usuários");
            System.out.println("13. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Senha: ");
                    String senha = sc.nextLine();

                    usuario usuario = new usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setDataCadastro(java.time.LocalDate.now());

                    usuarioDAO.salvar(usuario);
                    System.out.println("Usuário criado com sucesso!");
                    break;

                case 2:
                    List<usuario> usuarios = usuarioDAO.listarTodos();
                    System.out.println("=== LISTA DE USUÁRIOS ===");
                    for (usuario u : usuarios) {
                        System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome());
                    }
                    break;

                case 3:
                    System.out.print("ID do usuário: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();

                    usuario uAtualizar = usuarioDAO.buscarPorId(idAtualizar);

                    if (uAtualizar == null) {
                        System.out.println("Usuário não encontrado!");
                        break;
                    }

                    System.out.print("Novo nome: ");
                    uAtualizar.setNome(sc.nextLine());
                    System.out.print("Novo email: ");
                    uAtualizar.setEmail(sc.nextLine());
                    System.out.print("Nova senha: ");
                    uAtualizar.setSenha(sc.nextLine());

                    usuarioDAO.atualizar(uAtualizar);
                    System.out.println("Usuário atualizado!");
                    break;

                case 4:
                    System.out.print("ID do usuário: ");
                    usuarioDAO.deletar(sc.nextInt());
                    System.out.println("Usuário excluído com sucesso!");
                    break;

                case 5:
                    System.out.print("ID do usuário: ");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    postagem postagem = new postagem();
                    postagem.setUsuarioId(userId);

                    System.out.print("Conteúdo: ");
                    postagem.setConteudo(sc.nextLine());

                    postagem.setDataPostagem(java.time.LocalDate.now());

                    postagemDAO.salvar(postagem);
                    System.out.println("Postagem criada!");
                    break;

                case 6:
                    System.out.print("ID do usuário: ");
                    List<postagem> postagens = postagemDAO.listarPorUsuario(sc.nextInt());
                    System.out.println("=== POSTAGENS ===");
                    for (postagem p : postagens) {
                        System.out.println(p.getConteudo());
                    }
                    break;

                case 7:
                    System.out.print("ID da model.postagem: ");
                    postagemDAO.deletar(sc.nextInt());
                    System.out.println("Postagem excluída!");
                    break;

                case 8:
                    System.out.print("ID do usuário: ");
                    int idCurtidor = sc.nextInt();
                    System.out.print("ID da model.postagem: ");
                    int idPost = sc.nextInt();

                    if (!curtidaDAO.verificarSeJaCurtiu(idCurtidor, idPost)) {
                        curtidaDAO.curtir(idCurtidor, idPost);
                        System.out.println("Curtido!");
                    } else {
                        System.out.println("Você já curtiu esta model.postagem!");
                    }
                    break;

                case 9:
                    System.out.print("ID do usuário: ");
                    int idDescurtir = sc.nextInt();
                    System.out.print("ID da model.postagem: ");
                    int idPostDescurtir = sc.nextInt();

                    curtidaDAO.descurtir(idDescurtir, idPostDescurtir);
                    System.out.println("Curtida removida!");
                    break;

                case 10:
                    System.out.print("ID da model.postagem: ");
                    int idPostQtd = sc.nextInt();
                    int qtd = curtidaDAO.contarCurtidas(idPostQtd);
                    System.out.println("Curtidas: " + qtd);
                    break;

                case 11:
                    System.out.print("Remetente: ");
                    int remetente = sc.nextInt();
                    System.out.print("Destinatário: ");
                    int destinatario = sc.nextInt();
                    sc.nextLine();

                    direct direct = new direct();
                    direct.setRemetenteId(remetente);
                    direct.setDestinatarioId(destinatario);
                    System.out.print("Mensagem: ");
                    direct.setMensagem(sc.nextLine());
                    direct.setDataEnvio(java.time.LocalDate.now());

                    directDAO.enviarMensagem(direct);
                    System.out.println("Mensagem enviada!");
                    break;

                case 12:
                    System.out.print("ID 1: ");
                    int u1 = sc.nextInt();
                    System.out.print("ID 2: ");
                    int u2 = sc.nextInt();

                    List<direct> directs = directDAO.listarMensagens(u1, u2);

                    for (model.direct d : directs) {
                        System.out.println(d.getMensagem());
                    }
                    break;

                case 13:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}
