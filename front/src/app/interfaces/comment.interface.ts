import { Post } from "./post.interface";
import { User } from "./user.interface";

export interface Comment {
    commentId?: number;
    post: Post;
    user: User;
    content: string;
    createdAt?: string;
}