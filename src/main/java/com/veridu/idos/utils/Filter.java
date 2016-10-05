package com.veridu.idos.utils;

import java.util.HashMap;

public class Filter {

    protected HashMap<String, String> params = new HashMap<>();

    /**
     * Boolean value to get all pages
     */
    private boolean getAllPages = false;

    /**
     * Constructor Class
     */
    private Filter() {
    }

    /**
     * Return all parameters added in the HashMap<String, String>
     *
     * @return
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * Add a source name filter when using the source endpoint
     *
     * @param name
     * @return
     */
    public Filter addSourceNameFilter(String name) {
        return this.addFilterByKeyName("name", name);
    }

    /**
     * Adds a feature source name filter
     *
     * @param filter
     * @return
     */
    public Filter addFeatureSourceNameFilter(String filter) {
        return this.addFilterByKeyName("source", filter);
    }

    /**
     * Adds a raw source name filter
     *
     * @param filter
     * @return
     */
    public Filter addRawSourceNameFilter(String filter) {
        return this.addFilterByKeyName("source:name", filter);
    }

    /**
     * Use this filter to get features not related to a particular
     * source, but the profile itself (e.g., self submitted).
     *
     * @return self
     */
    public Filter addSourceIDNullFilter() {
        return this.addFilterByKeyName("source:id", "0");
    }

    /**
     * Filter by source id
     *
     * @param id
     * @return
     */
    public Filter addSourceIDFilter(int id) {
        return this.addFilterByKeyName("source:id", String.valueOf(id));
    }

    /**
     * Filter using a sort - true for ascending
     *
     * @return
     */
    public Filter addSortFilter(SortFilterType type) {
        return this.addFilterByKeyName("filter:sort", String.valueOf(type));
    }

    /**
     * Add an order by fitler
     *
     * @param parameter
     * @return
     */
    public Filter addOrderByFilter(String parameter) {
        return this.addFilterByKeyName("filter:order", parameter);
    }

    public Filter addRawFilterOrderLatest() {
        return this.addFilterByKeyName("filter:order", "latest");
    }

    /**
     * Limit the number of results
     *
     * @param limit
     * @return
     */
    public Filter addLimitFilter(int limit) {
        return this.addFilterByKeyName("filter:limit", String.valueOf(limit));
    }

    /**
     * Filter by source collection
     *
     * @param collection
     * @return
     */
    public Filter addCollectionFilter(String collection) {
        return this.addFilterByKeyName("collection", collection);
    }

    /**
     * Adds a name filter
     *
     * @param filter
     * @return
     */
    public Filter addNameFilter(String filter) {
        return this.addFilterByKeyName("name", filter);
    }

    /**
     * Adds a slug filter
     *
     * @param filter
     * @return
     */
    public Filter addSlugFilter(String filter) {
        return this.addFilterByKeyName("slug", filter);
    }

    /**
     * Adds a creator filter
     *
     * @param filter
     * @return
     */
    public Filter addCreatorFilter(String filter) {
        this.addFilterByKeyName("creator", filter);
        return this;
    }

    /**
     * Adds a type filter
     *
     * @param filter
     * @return
     */
    public Filter addTypeFilter(String filter) {
        return this.addFilterByKeyName("type", filter);
    }

    /**
     * Add a filter passing the name of the filter parameter.
     *
     * @param name
     *            parameter on which to run the filter
     * @param filter
     *            actual filter value
     *
     * @return self
     */
    public Filter addFilterByKeyName(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    /**
     * Sets the pagination number and adds it to the filter
     *
     * @param page
     * @return
     */
    public Filter addPageNumber(int page) {
        return this.addFilterByKeyName("page", String.valueOf(page));
    }

    /**
     * Sets the limit per page and adds it to the filter
     *
     * @param limitPerPage
     * @return
     */
    public Filter addLimitPerPage(int limitPerPage) {
        return this.addFilterByKeyName("perPage", String.valueOf(limitPerPage));
    }

    /**
     * Factory to create a new Filter object
     *
     * @return
     */
    public static Filter createFilter() {
        return new Filter();
    }

    /**
     * Overrides the toString() method
     */
    @Override
    public String toString() {
        int size = this.params.keySet().size();

        String[] queryElements = new String[size];

        int i = size - 1;
        for (String key : this.params.keySet())
            queryElements[i--] = key + "=" + this.params.get(key);

        return String.join("&", queryElements);
    }

    /**
     * Sets the getAllPages property to true
     */
    public void setAllPagesTrue() {
        this.getAllPages = true;
    }

    /**
     * Return the boolean value of the property getAllPages
     *
     * @return
     */
    public boolean getAllPagesTrue() {
        return this.getAllPages;
    }
}
