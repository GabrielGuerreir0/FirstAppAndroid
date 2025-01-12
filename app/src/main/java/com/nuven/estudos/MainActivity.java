package com.nuven.estudos;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private PreviewView previewView;
    private static final int PERMISSION_REQUEST_CODE = 2;
    private ProcessCameraProvider cameraProvider;
    private androidx.camera.core.Preview preview;
    private boolean isCameraOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o PreviewView e o botão
        previewView = findViewById(R.id.previewView);
        Button btnCapture = findViewById(R.id.buttonCapture);

        // Configura o que acontece quando o botão é pressionado
        btnCapture.setOnClickListener(v -> toggleCamera(btnCapture));
    }

    private void toggleCamera(Button btnCapture) {
        if (isCameraOpened) {
            // feichar a câmera se já estiver aberta
            stopCamera();
            btnCapture.setText("Abrir Câmera");
        } else {
            // Abrir a câmera se não estiver aberta
            if (checkCameraPermissions()) {
                startCamera();
                btnCapture.setText("Fechar Câmera");
            } else {
                requestPermissions();
            }
        }
    }

    private void startCamera() {
        //Iniciar camera
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                // Obtém o ProcessCameraProvider
                cameraProvider = cameraProviderFuture.get();

                // Seleciona a câmera traseira ou frontal
                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();

                // Cria o Preview
                preview = new androidx.camera.core.Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                // Vincula o ciclo de vida da câmera ao da atividade
                cameraProvider.bindToLifecycle(this, cameraSelector, preview);

                // Marca que a câmera foi aberta
                isCameraOpened = true;

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Erro ao iniciar a câmera", Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void stopCamera() {
        // Libera a câmera
        if (cameraProvider != null) {
            cameraProvider.unbindAll();
            isCameraOpened = false;
        }
    }

    private boolean checkCameraPermissions() {
        // Verifica se as permissões de câmera
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        // Solicita permissões de câmera
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, agora inicia a câmera
                startCamera();
            } else {
                // Permissão negada
                Toast.makeText(this, "Permissão negada para acessar a câmera", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
