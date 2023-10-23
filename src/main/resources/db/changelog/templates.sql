INSERT INTO templates (id, "template", title, template_type, status)
VALUES
  (uuid_generate_v4(), '<!DOCTYPE html> <html>   <head>     <style>       body {         font-family: Arial, sans-serif;         background-color: #f4f4f4;         margin: 0;         padding: 0;       }        .container {         max-width: 600px;         margin: 0 auto;         padding: 20px;       }        .header {         background-color: #000;         color: #fff;         text-align: center;         padding: 20px;       }        .content {         background-color: #fff;         padding: 20px;         border-radius: 5px;       }        .button {         display: inline-block;         background-color: #000;         color: #fff;         padding: 10px 20px;         text-decoration: none;         border-radius: 5px;       }        .message {         margin-top: 20px;       }        .emoji {         font-size: 24px;       }     </style>   </head>   <body>     <div class="container">       <div class="header">         <h1>Bem-vindo ao Meu Sistema <span class="emoji">😃</span></h1>       </div>       <div class="content">         <p>Olá, {name}.</p>         <p>           Obrigado por criar uma conta em nosso sistema. Estamos empolgados em           tê-lo conosco! <span class="emoji">🎉</span>         </p>         <p class="message">           Por favor, clique no botão abaixo para confirmar sua conta:         </p>         <a class="button" href="{url}">Confirmar Conta</a>       </div>     </div>   </body> </html>', 'Bem vindo {name}!', 1, true),
  (uuid_generate_v4(), '<!DOCTYPE html> <html>   <head>     <style>       body {         font-family: Arial, sans-serif;         background-color: #f4f4f4;         margin: 0;         padding: 0;       }        .container {         max-width: 600px;         margin: 0 auto;         padding: 20px;       }        .header {         background-color: #000;         color: #fff;         text-align: center;         padding: 20px;       }        .content {         background-color: #fff;         padding: 20px;         border-radius: 5px;       }        .button {         display: inline-block;         background-color: #000;         color: #fff;         padding: 10px 20px;         text-decoration: none;         border-radius: 5px;       }        .message {         margin-top: 20px;       }        .emoji {         font-size: 24px;       }     </style>   </head>   <body>     <div class="container">       <div class="header">         <h1>Bem-vindo ao Meu Sistema <span class="emoji">😃</span></h1>       </div>       <div class="content">         <p>Olá, {name}.</p>         <p>           Obrigado por criar uma conta em nosso sistema. Estamos empolgados em           tê-lo conosco! <span class="emoji">🎉</span>         </p>         <p class="message">           Por favor, clique no botão abaixo para confirmar sua conta:         </p>         <a class="button" href="{url}">Confirmar Conta</a>       </div>     </div>   </body> </html>', 'Bem vindo {name}!', 0, true),
  (uuid_generate_v4(), '<!DOCTYPE html> <html>   <head>     <style>       body {         font-family: Arial, sans-serif;         background-color: #f4f4f4;         margin: 0;         padding: 0;       }        .container {         max-width: 600px;         margin: 0 auto;         padding: 20px;       }        .header {         background-color: #000;         color: #fff;         text-align: center;         padding: 20px;       }        .content {         background-color: #fff;         padding: 20px;         border-radius: 5px;       }        .message {         margin-top: 20px;       }        .emoji {         font-size: 24px;       }        .link {         text-decoration: underline;         color: #4ade80;       }     </style>   </head>   <body>     <div class="container">       <div class="header">         <h1>Alteração de Senha <span class="emoji">🔒</span></h1>       </div>       <div class="content">         <p>Olá,{name}</p>         <p>           Este é um aviso para informar que a senha da sua conta em nosso           sistema foi alterada recentemente. Se você não solicitou essa           alteração, entre em contato conosco imediatamente.         </p>         <p class="message">           Se você realizou essa alteração, por favor, ignore este e-mail.         </p>         <p>Atenciosamente equipe NiceTry.</p>         <a class="link" href="{whatsApp}"           >Tire suas dúvidas conosco via Whatsapp</a         >         <div>Atendimento de 9h às 18h em dias úteis</div>       </div>       </div>            </div>   </body> </html>', 'Sua senha acaba de ser alterada', 3, true),
  (uuid_generate_v4(), '<!DOCTYPE html> <html>   <head>     <style>       body {         font-family: Arial, sans-serif;         background-color: #f4f4f4;         margin: 0;         padding: 0;       }        .container {         max-width: 600px;         margin: 0 auto;         padding: 20px;       }        .header {         background-color: #000;         color: #fff;         text-align: center;         padding: 20px;       }        .content {         background-color: #fff;         padding: 20px;         border-radius: 5px;       }        .button {         display: inline-block;         background-color: #000;         color: #fff;         padding: 10px 20px;         text-decoration: none;         border-radius: 5px;       }        .message {         margin-top: 20px;       }        .emoji {         font-size: 24px;       }        .link {         text-decoration: underline;         color: #4ade80;       }     </style>   </head>   <body>     <div class="container">       <div class="header">         <h1>Campanha da {campaignName}<span class="emoji">🤞</span></h1>       </div>       <div class="content">         <p>Olá, {name} faça o pagamento</p>         <p>           Você está recebendo este e-mail porque você acaba de participar da campanha da {campaignName}:         </p>         <a class="button" href="{url}">Link para pagamento</a>         <p class="message">           Se você ja realizou o pagamento no site, por favor, ignore este           e-mail.         </p>         <p>Atenciosamente equipe NiceTry.</p>         <a class="link" href="{whatsApp}"           >Tire suas dúvidas conosco via Whatsapp</a         >         <div>Atendimento de 9h às 18h em dias úteis</div>       </div>       </div>       </div>     </div>   </body> </html>', 'Realizar Pagamento', 4, true);



INSERT INTO users (id, "name", cpf, email, phone_number, "password", trade_link, "permission", status, create_date)
VALUES
  (uuid_generate_v4(), 'Rafael Pereira Dos Santos', '11455703990', 'rafinhapsantos50@gmail.com', NULL, '$2a$10$VhybHOThoqCkaHpHhlkAQOj82Og8DemCaZfDD3ZxJcqUZH1.Ic84C', 'tradelinknovo', '0', false, '2023-10-05 12:26:37.714');
