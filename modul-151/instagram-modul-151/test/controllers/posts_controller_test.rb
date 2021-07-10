require 'test_helper'

class PostsControllerTest < ActionDispatch::IntegrationTest
  test "should get index" do
    get posts_index_url
    assert_response :success
  end

  test "should get create" do
    get posts_create_url
    assert_response :success
  end

  test "should get show" do
    get posts_show_url
    assert_response :success
  end

  test "should get destroy" do
    get posts_destroy_url
    assert_response :success
  end

  test "should get find_post" do
    get posts_find_post_url
    assert_response :success
  end

  test "should get post_params" do
    get posts_post_params_url
    assert_response :success
  end

end
