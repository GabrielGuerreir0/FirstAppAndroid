# CameraX Application

Este é um projeto Android desenvolvido em Java que utiliza a biblioteca **CameraX** para capturar imagens e pré-visualizar o feed da câmera. O aplicativo possui uma funcionalidade básica que permite alternar entre abrir e fechar a câmera, verificando e solicitando permissões de uso da câmera, conforme necessário.

## 🛠️ Tecnologias Utilizadas

- **Java** - Linguagem principal do projeto.
- **CameraX** - Biblioteca da Jetpack para integrar recursos avançados de câmera.
- **AndroidX** - Suporte a bibliotecas modernas para desenvolvimento Android.
- **Material Design** - Para criar uma interface de usuário moderna e acessível.

## 📋 Funcionalidades

- Pré-visualização da câmera (traseira por padrão).
- Verificação e solicitação de permissões de câmera em tempo de execução.
- Alternância entre abrir e fechar a câmera com um botão.
- Interface simples com o uso do `PreviewView` para exibir o feed da câmera.

## 📂 Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/com/nuven/estudos/
│   │   └── MainActivity.java         # Tela principal com a funcionalidade de câmera
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml     # Layout principal com o PreviewView e botão
│   │   └── values/
│   │       ├── strings.xml           # Strings utilizadas na aplicação
│   │       └── themes.xml            # Estilos e temas da aplicação
│   └── AndroidManifest.xml           # Configurações do aplicativo e permissões
```

# Configuração Completa do Projeto CameraX em Java

## 1️⃣ Criar um Novo Projeto no Android Studio

1. Abra o **Android Studio**.
2. Crie um novo projeto selecionando a opção **Empty Activity**.
3. Configure:
   - Nome do projeto: `CameraXApp`.
   - Nome do pacote: `com.nuven.estudos`.
   - Linguagem: **Java**.
   - API mínima: **21 (Android 5.0 - Lollipop)**.
4. Finalize a criação do projeto.

---

## 2️⃣ Configurar o Arquivo `AndroidManifest.xml`

Adicione as permissões de uso da câmera e o recurso obrigatório no arquivo `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.camera" android:required="true" />
```
