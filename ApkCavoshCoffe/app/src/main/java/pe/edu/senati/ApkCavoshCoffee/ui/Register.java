package pe.edu.senati.ApkCavoshCoffee.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import pe.edu.senati.ApkCavoshCoffee.MainActivity;
import pe.edu.senati.ApkCavoshCoffee.R;
import pe.edu.senati.ApkCavoshCoffee.controller.UsuarioController;
import pe.edu.senati.ApkCavoshCoffee.databinding.FragmentRegisterBinding;
import pe.edu.senati.ApkCavoshCoffee.model.Usuario;

public class Register extends Fragment {
    FragmentRegisterBinding binding;
    View view;
    Context context;
    NavController navController;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);
        return view = binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        navController = Navigation.findNavController(view);
        binding.btnSignIn.setOnClickListener(view1 -> navController.navigate(R.id.navigation_login));
        binding.tvLoginNow.setOnClickListener(view1 -> navController.navigate(R.id.navigation_login));
        binding.btnRegisterForm.setOnClickListener(view1 -> btnRegisterForm_click());
    }

    private void btnRegisterForm_click() {
        String sNombres= binding.edtFullName.getText().toString();
        String sCorreo= binding.edtEmail.getText().toString();
        String sPasswordd= binding.edtPasswordd.getText().toString();
        String sPassworddConfirm= binding.edtConfirmPasswordd.getText().toString();

        if (sNombres.isEmpty() || sCorreo.isEmpty() || sPasswordd.isEmpty() || sPassworddConfirm.isEmpty()){
            Snackbar.make(view,"No se puede dejar campos vacios",Snackbar.LENGTH_LONG).show();
            return;
        }
        if (!sPasswordd.equals(sPassworddConfirm)){
            Snackbar.make(view,"Las contraseñas no son iguales",Snackbar.LENGTH_LONG).show();
            return;
        }

        Usuario usuario = new Usuario();

        usuario.setNombres(sNombres);
        usuario.setCorreo(sCorreo);
        usuario.setPasswordd(sPasswordd);

        usuario.setId(1);
        usuario.setLogin(1);

        MainActivity.usuario = usuario;
        UsuarioController controller = new UsuarioController(context);

        controller.Register(usuario);

        navController.navigate(R.id.navigation_home);

    }
}