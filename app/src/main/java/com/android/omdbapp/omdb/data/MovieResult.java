package com.android.omdbapp.omdb.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResult {

@SerializedName("Search")
private List<Movie> search = null;
@SerializedName("totalResults")
private String totalResults;
@SerializedName("Response")
private String response;

public List<Movie> getSearch() {
return search;
}

public void setSearch(List<Movie> search) {
this.search = search;
}

public String getTotalResults() {
return totalResults;
}

public void setTotalResults(String totalResults) {
this.totalResults = totalResults;
}

public String getResponse() {
return response;
}

public void setResponse(String response) {
this.response = response;
}

}