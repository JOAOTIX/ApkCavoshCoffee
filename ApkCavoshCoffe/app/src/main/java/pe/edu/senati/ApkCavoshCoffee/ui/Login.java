package pe.edu.senati.ApkCavoshCoffee.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import pe.edu.senati.ApkCavoshCoffee.MainActivity;
import pe.edu.senati.ApkCavoshCoffee.R;
import pe.edu.senati.ApkCavoshCoffee.controller.UsuarioController;
import pe.edu.senati.ApkCavoshCoffee.databinding.FragmentLoginBinding;
import pe.edu.senati.ApkCavoshCoffee.model.Usuario;

public class Login extends Fragment {

    private FirebaseAuth mAuth;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private FragmentLoginBinding binding;
    private NavController navController;
    View view;
    Context context;


    private final ActivityResultLauncher<IntentSenderRequest> googleSignInLauncher =
            registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    try {
                        SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                        String idToken = credential.getGoogleIdToken();
                        if (idToken != null) {
                            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                            mAuth.signInWithCredential(firebaseCredential)
                                    .addOnCompleteListener(requireActivity(), task -> {
                                        if (task.isSuccessful()) {
                                            navController.navigate(R.id.navigation_home);
                                        } else {
                                            if (task.getException() != null) {
                                                task.getException().printStackTrace();
                                            }
                                        }
                                    });
                        }
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
            });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();
        context= getContext();

        // Botones para ir al registro
        binding.btnLogin.setOnClickListener( v -> btnLogin_Click());
        binding.btnRegister.setOnClickListener(v -> navController.navigate(R.id.navigation_register));
        binding.tvRegisterNow.setOnClickListener(v -> navController.navigate(R.id.navigation_register));
        binding.tvForgotPasswordd.setOnClickListener(v -> navController.navigate((R.id.navigation_password)));

        // Configuración de Google One Tap
        oneTapClient = Identity.getSignInClient(requireContext());
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                .setSupported(true)
                                .setServerClientId(getString(R.string.default_web_client_id))
                                .setFilterByAuthorizedAccounts(false)
                                .build())
                .build();

        // Botón de Google
        binding.ibGoogle.setOnClickListener(v -> signInWithGoogle());
    }

    private void btnLogin_Click() {
        String sCorreo= binding.edtEmail.getText().toString().trim();
        String sPasswordd= binding.edtPassword.getText().toString();

        if(sCorreo.isEmpty() || sPasswordd.isEmpty()){
            Snackbar.make(view, "Correo y/o password invalido", Snackbar.LENGTH_LONG).show();
            return;
        }
        Usuario usuario = new Usuario();
        usuario.setCorreo(sCorreo);
        usuario.setPasswordd(sPasswordd);

        UsuarioController controller = new UsuarioController(context);
        controller.Login(usuario);
        if (usuario.isLogin()){
            MainActivity.usuario = usuario;
            navController.navigate(R.id.navigation_home);
        }
        Snackbar.make(view,"Correo o password invalido o usuario no registrado",Snackbar.LENGTH_LONG).show();
    }

    private void signInWithGoogle() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(requireActivity(), result -> {
                    IntentSenderRequest intentSenderRequest =
                            new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender())
                                    .build();
                    googleSignInLauncher.launch(intentSenderRequest);
                })
                .addOnFailureListener(requireActivity(), e -> {
                    e.printStackTrace();
                });
    }
}
