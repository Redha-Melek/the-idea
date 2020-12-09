package com.redha.idea.constant;

/**
 * Constantes pour l'API REST.
 */
public final class ApiConstants {

    private ApiConstants() {
        // pas de construction directe
    }

    /** chemin de base de l'API */
    public static final String API_ROOT = "";

    /** chemin de l'API des users */
    public static final String USER_ROOT = API_ROOT + "/user";

    /** chemin de l'API des ideas */
    public static final String IDEA_ROOT = API_ROOT + "/idea";

    /** paramètre : ID */
    public static final String PARAM_ID = "id";

    /** paramètre : name */
    public static final String PARAM_NAME = "name";

    /** sous-chemin de ressource avec un ID */
    public static final String SUBPATH_ID = "/{" + PARAM_ID + "}";

    /** sous-chemin de ressource avec un nom de l'un des auteurs d'une idea */
    public static final String SUBPATH_IDEA_USER_NAME = "/user/{" + PARAM_NAME+ "}";
}