package no.mesan.mesanquiz.common;

import android.content.Context;

import com.microsoft.aad.adal.AuthenticationResult;

import no.mesan.mesanquiz.model.GameDto;

public class ValueHolder {

    private Context context;
    private static ValueHolder valueHolder;
    private String accessToken;
    private AuthenticationResult authenticationResult;
    private GameDto newGame;

    private ValueHolder(Context context) {
        this.context = context;
    }

    public static ValueHolder getInstance(Context context) {
        if(valueHolder == null) {
            valueHolder = new ValueHolder(context);
        }

        return valueHolder;
    }

    public String getAccessToken() {
        if (authenticationResult != null) {
            return authenticationResult.getAccessToken();
        }
        return null;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthenticationResult getAuthenticationResult() {
        return authenticationResult;
    }

    public void setAuthenticationResult(AuthenticationResult authenticationResult) {
        this.authenticationResult = authenticationResult;
    }

    public GameDto getNewGame() {
        return newGame;
    }

    public void createNewGameTemp() {
        this.newGame = new GameDto();
    }
}
