package com.manuelluvuvamo.portfolio.common;

/**
 * Documentos acessiveis por slug na API publica. O servico gera o slug
 * a partir do titulo quando o cliente nao o envia.
 */
public interface Sluggable {

    String getSlug();

    void setSlug(String slug);

    /** Texto base para gerar o slug quando este vem vazio. */
    String slugSource();
}
