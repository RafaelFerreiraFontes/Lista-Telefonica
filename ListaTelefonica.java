import java.util.Scanner;

public class ListaTelefonica
{
  static Agenda agenda = new Agenda();

  public static void main(String[] args) 
  {     
     Scanner in = new Scanner(System.in);
      
     boolean loop = true;

     while(loop)
     {
         int opc;

         System.out.print("     [1]: Inserir um contato.\n" + 
                          "     [2]: Remover um contato.\n" +
                          "     [3]: Alterar um contato.\n" +
                          "     [4]: Consultar um contato.\n" +
                          "     [5]: Listar agenda.\n" +
                          "     [6]: Sair do programa.\n\n" +
                          "     Sua opcao:"
                         );
   
         opc = in.nextInt();
         
         in.nextLine();

         switch(opc)
         {
             case 1:
             {
               System.out.println("Digite as informacoes do contato.\n");
               
               String nome,end;
               int num;
   
               System.out.println("Nome do contato:");

               nome = in.nextLine();
   
               System.out.println("Endereco do contato:");
   
               end = in.nextLine();
   
               System.out.println("Numero do contato:");
   
               num = in.nextInt();
   
               int pos = agenda.BuscarNaAgenda(nome);
   
               if(pos != -1)
               {
                  if(agenda.Alterar(pos, nome, end, num))
                     System.out.println("Alteracao do contato " + nome + " feita com sucesso.\n");
                  else
                     System.out.println("Falha na alteracao do contato " + nome + "\n");
               }
               else
               {
                  if(agenda.Inserir(nome, end, num))
                     System.out.println("Insercao do contato " + nome + " feita com sucesso.\n");
                  else
                     System.out.println("Falha na insercao do contato " + nome + "\n");
               }
   
             }
             break;
   
            case 2:
            {
               System.out.println("Digite o nome do contato para remover da agenda:");
               String nome = in.nextLine();

               if(agenda.Remover(nome))
                  System.out.println("Remocao do contato "+ nome +" feita com sucesso.");
               else
                  System.out.println("Contato nao encontrado.");
            }
            break;
               
            case 3:
            {
               System.out.println("Digite as informacoes do contato.\n");
               
               String nome,end;
               int num;
   
               System.out.println("Nome do contato:");
   
               nome = in.nextLine();
   
               System.out.println("Endereco do contato:");
   
               end = in.nextLine();
   
               System.out.println("Numero do contato:");
   
               num = in.nextInt();
   
               int pos = agenda.BuscarNaAgenda(nome);
   
               if(pos != -1)
               {
                  if(agenda.Alterar(pos, nome, end, num))
                     System.out.println("Alteracao do contato " + nome + " feita com sucesso.\n");
                  else
                     System.out.println("Falha na alteracao do contato " + nome + "\n");
               }
               else
               {
                  System.out.println("Contato nao encontrado.");
               }
            }
            break;
   
            case 4:
            {
               System.out.println("Digite o nome do contato para consultar da agenda:");
               String nome = in.nextLine();

               int pos = agenda.BuscarNaAgenda(nome);

               if(pos != -1)
                  System.out.println(agenda.contatos[pos].toString() + "\n");
               else
                  System.out.println("Contato nao encontrado.");
            }  
            break;
   
            case 5:
               System.out.println("Lista de Contatos:");
               agenda.Listar();
            break;
               
            case 6:
               System.out.println("Fim do Programa");
               loop = false;
            break;

            default:
               System.out.println("Entrada invalida.");
         }
     };

     agenda.contatos = null;
     in.close();
  }   
}

class Agenda
{
   //Lista dos Contatos.
   public Contato[] contatos;
   
   public Agenda()
   {
      contatos = new Contato[1000];//Inicializa lista com 1000 contatos.
   }

   //Lista todos os contatos da lista.
   public void Listar()
   {
      for (Contato contato : contatos) 
      {
         if(contato != null)
         System.out.println(contato.toString() + "\n");
      }
   }

   //Inseri um contato na primeira posicao nula da lista.
   public boolean Inserir(String nome,String end,int numero)
   {
      int pos = BuscarNaAgenda((Contato)null);
      
      if (pos != -1)
      {
         contatos[pos] = new Contato(nome,end,numero);
         
         return true;
      }

      return false;
   }

   //Altera um contato existente na lista.
   public boolean Alterar(int pos,String nome,String end,int numero)
   {
      if(pos != -1)
      {
         contatos[pos].SetNome((nome.equals("")) ? contatos[pos].GetNome() : nome);
         
         contatos[pos].SetEndereco((end.equals("")) ? contatos[pos].GetEndereco() : end);
         
         contatos[pos].SetNumero((numero == 0) ? contatos[pos].GetNumero() : numero);
            
         return true;
      }

      return false;
   }

   //Remove um contato existente na lista. 
   public boolean Remover(String nome)
   {
      int pos = BuscarNaAgenda(nome);

      if(pos != -1)
      {
         contatos[pos] = null;

         return true;
      }

      return false;
   }
   
   //Busca um contato na lista por nome.
   public int BuscarNaAgenda(String nome)
   {
      for(int i = 0;i < contatos.length;i++)
      {
         if(contatos[i] != null)
         {
            if(contatos[i].GetNome().equals(nome))
            {
               return i;
            }
         }
      }

      return -1;
   }

   //Busca um contato na lista por referenecia.
   public int BuscarNaAgenda(Contato obj)
   {
      for(int i = 0;i < contatos.length;i++)
      {
         if(contatos[i] != null && contatos[i].equals(obj))
         {
            return i;
         }
         else if(contatos[i] == obj)
         {
            return i;
         }
      }

      return -1;
   }
}

class Contato
{ 
   private String nome;//Nome do contato.
   private String endereco;//Endereco do contato.
   private int numero;//Numero do contato.

   //Inicializa o contato com valores padrao.
   public Contato()
   {
      this.nome = "";

      this.endereco = "";
      
      this.numero = 0; 
   }

   //Inicializa o contato com valores passados por parametro.
   public Contato(String nome,String end,int num)
   {
      this.nome = nome;
      
      this.endereco = end;
      
      this.numero = num; 
   }

   //Atribui um valor ao campo nome.
   public void SetNome(String nome)
   {
      this.nome = nome;
   }

   //Atribui um valor ao campo endereco.
   public void SetEndereco(String end)
   {
      this.endereco = end;
   }

   //Atribui um valor ao campo Numero.
   public void SetNumero(int num)
   {
      this.numero = num;
   }

   //Obtem o valor do campo nome.
   public String GetNome()
   {
      return nome;
   }

   //Obtem o valor do campo endereco.
   public String GetEndereco()
   {
      return endereco;
   }

   //Obtem o valor do campo numero.
   public int GetNumero()
   {
      return numero;
   }

   //Coverte as informacoes do contato para string formatada.
   public String toString()
   {
      return ("Nome:" + nome + "      Telefone:" + numero + "    Endereco:" + endereco);
   }
}