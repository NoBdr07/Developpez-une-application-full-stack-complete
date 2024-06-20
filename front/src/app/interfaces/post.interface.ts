import { Topic } from "./topic.interface";
import { User } from "./user.interface";

export interface Post {
    postId: number;
    title: string;
    content: string;
    createdAt: Date;
    user: User;
    topic: Topic;
}