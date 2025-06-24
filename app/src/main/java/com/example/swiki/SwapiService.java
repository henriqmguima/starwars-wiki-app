package com.example.swiki;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SwapiService {
    @GET("people")
    Call<PeopleResponse> getPeople();
    @GET("people/{id}")
    Call<CharacterResponse> getCharacterById(@Path("id") String id);
    @GET("films")
    Call<FilmsResponse> getFilms();
    @GET("films/{id}")
    Call<FilmDetailResponse> getFilmById(@Path("id") String id);

}
